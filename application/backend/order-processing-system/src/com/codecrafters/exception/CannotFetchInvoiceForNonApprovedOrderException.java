package com.codecrafters.exception;

public class CannotFetchInvoiceForNonApprovedOrderException extends RuntimeException{
    public CannotFetchInvoiceForNonApprovedOrderException(String message) {
        super(message);
    }
}
