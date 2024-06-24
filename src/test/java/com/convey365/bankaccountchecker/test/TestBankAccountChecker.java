package com.convey365.bankaccountchecker.test;

import com.convey365.bankaccountchecker.model.AccountDetails;
import com.convey365.bankaccountchecker.model.AccountValidationResult;
import com.convey365.bankaccountchecker.service.AccountSortCodeCheckerService;
import org.junit.jupiter.api.Assertions;

public class TestBankAccountChecker {

    private String apiKey = "57b034f9f99a077b7a05f01ae0a54c9d";
    private String password = "wW4Tg2MZnZw$";

    //@Test
    public void testBankAccountChecker() {
        try (AccountSortCodeCheckerService service = new AccountSortCodeCheckerService(apiKey, password)) {
            runTest(service, "60-17-12", "29914701", 1);
            runTest(service, "089999", "66374959", 2);
            runTest(service, "871427", "46238510", 2);
        }
    }

    protected void runTest(AccountSortCodeCheckerService service, String sortCode, String account, int expectedResult) {
        AccountValidationResult result = service.validateBankAccount(new AccountDetails().withAccountDetails(sortCode, account));
        Assertions.assertEquals(result.getResultCode(), expectedResult);
    }
}
