package com.convey365.bankaccountchecker.model.exceptions;

public class InvalidAccountDetailsException extends BankAccountCheckerException {

    private static final long serialVersionUID = -6550121335745652031L;

    public InvalidAccountDetailsException(final String message) {
        super(message);
    }
}
