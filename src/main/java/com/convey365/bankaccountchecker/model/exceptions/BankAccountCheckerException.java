package com.convey365.bankaccountchecker.model.exceptions;

public abstract class BankAccountCheckerException extends RuntimeException {

    private static final long serialVersionUID = -5054278454443736577L;

    public BankAccountCheckerException(final String message) {
        super(message);
    }

    public BankAccountCheckerException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
