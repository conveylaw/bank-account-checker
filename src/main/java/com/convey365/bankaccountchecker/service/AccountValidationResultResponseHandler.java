package com.convey365.bankaccountchecker.service;

import com.convey365.bankaccountchecker.model.AccountValidationResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountValidationResultResponseHandler implements HttpClientResponseHandler<AccountValidationResult> {

    private final static Logger LOGGER = Logger.getLogger(AccountValidationResultResponseHandler.class.getName());
    private final ObjectMapper objectMapper;

    public AccountValidationResultResponseHandler(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public AccountValidationResult handleResponse(ClassicHttpResponse response) throws HttpException, IOException {
        int status = response.getCode();
        if ((status >= 200) && (status < 300)) {
            try {
                return objectMapper.readValue(response.getEntity().getContent(), AccountValidationResult.class);
            } catch (IOException | UnsupportedOperationException ex) {
                LOGGER.log(Level.WARNING, String.format("Error dealing with response JSON: %s", ex.getMessage()));
                throw new ClientProtocolException(String.format("Error dealing with response JSON: %s", ex.getMessage()));
            }
        } else {
            LOGGER.log(Level.WARNING, String.format("BankAccountChecker Request was not successful: %d:%s", status, response.getReasonPhrase()));
            throw new ClientProtocolException(String.format("BankAccountChecker Request was not successful: %d:%s", status, response.getReasonPhrase()));
        }
    }
}
