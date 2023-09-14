package com.codecrafters.exception;

public class InvoiceFetchingFailedException extends RuntimeException{
    public InvoiceFetchingFailedException(String message) {
        super(message);
    }
}
