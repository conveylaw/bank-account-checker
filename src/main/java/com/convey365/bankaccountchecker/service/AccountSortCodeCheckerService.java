package com.convey365.bankaccountchecker.service;

import com.convey365.bankaccountchecker.model.AccountDetails;
import com.convey365.bankaccountchecker.model.AccountValidationResult;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.convey365.bankaccountchecker.model.exceptions.ValidationAttemptException;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.net.URIBuilder;

import java.io.Closeable;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountSortCodeCheckerService implements Closeable {

    private final Logger LOGGER = Logger.getLogger(AccountSortCodeCheckerService.class.getName());
    private final String apiKey;
    private final String password;
    private final static String endpoint = "https://www.bankaccountchecker.com/listener.php";
    private ObjectMapper objectMapper;
    private CloseableHttpClient client = null;
    private RequestConfig config = RequestConfig.custom()
            .setResponseTimeout(20, TimeUnit.SECONDS)
            .setConnectionRequestTimeout(10, TimeUnit.SECONDS)
            .build();

    public AccountSortCodeCheckerService(final String apiKey, final String password) {
        this.apiKey = apiKey;
        this.password = password;
        initialise();
    }

    private void initialise() {
        client = HttpClients.custom()
                .setDefaultRequestConfig(config)
                .build();
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Override
    public void close() {
        try {
            client.close();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, String.format("Error closing HttpClient: %s", ex.getMessage()), ex);
        }
    }

    public AccountValidationResult validateBankAccount(AccountDetails account) {
        try {
            URIBuilder builder = new URIBuilder(endpoint);
            builder.setParameter("key", apiKey)
                    .setParameter("password", password)
                    .setParameter("output", "json")
                    .setParameter("type", "uk")
                    .setParameter("bankaccount", account.getAccountNumber())
                    .setParameter("sortcode", account.getSortCode());
            HttpGet get = new HttpGet(builder.build());
            AccountValidationResult result = client.execute(get, new AccountValidationResultResponseHandler(objectMapper));
            result.setDetails(account);
            result.setChecked(LocalDateTime.now());
            LOGGER.log(Level.INFO, String.format("Result from Bank Account Checker: %s", result));
            return result;
        } catch (Throwable t) {
            LOGGER.log(Level.WARNING, String.format("Exception thrown whilst executing bankaccountchecker: %s", t.getMessage()), t);
            throw new ValidationAttemptException(String.format("Exception thrown whilst executing bankaccountchecker: %s", t.getMessage()), t);
        }
    }
}
