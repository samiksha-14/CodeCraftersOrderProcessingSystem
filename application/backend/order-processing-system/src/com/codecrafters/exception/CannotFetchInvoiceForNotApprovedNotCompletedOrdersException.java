package com.codecrafters.exception;

public class CannotFetchInvoiceForNotApprovedNotCompletedOrdersException extends RuntimeException {
    public CannotFetchInvoiceForNotApprovedNotCompletedOrdersException(String message) {
        super(message);
    }
}
