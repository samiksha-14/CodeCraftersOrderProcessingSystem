package com.codecrafters.exception;

// Custom exception class that extends RuntimeException
public class CannotFetchInvoiceForNonApprovedOrderException extends RuntimeException {

    // Constructor that takes a message as input
    public CannotFetchInvoiceForNonApprovedOrderException(String message) {
        // Call the constructor of the superclass (RuntimeException) with the provided message
        super(message);
    }
}
