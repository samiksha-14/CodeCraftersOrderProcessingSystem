package com.codecrafters.exception;

public class OrderLoadingFailureException extends RuntimeException {
    public OrderLoadingFailureException(String message) {
        super(message);
    }
}
