package com.codecrafters.exception;

public class OrderDateCannotBePreviousDateException extends RuntimeException {
    public OrderDateCannotBePreviousDateException(String message) {
        super(message);
    }
}
