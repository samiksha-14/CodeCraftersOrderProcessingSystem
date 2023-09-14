package com.codecrafters.exception;

public class OrderStatusUpdateFailureException extends RuntimeException {
    public OrderStatusUpdateFailureException(String message) {
        super(message);
    }
}
