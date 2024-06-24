package com.convey365.bankaccountchecker.model.exceptions;

public class ValidationAttemptException extends BankAccountCheckerException {

    private static final long serialVersionUID = -3705455101207808609L;
    
    public ValidationAttemptException(final String message, final Throwable cause) {
        super(message, cause);
    }
    
}
