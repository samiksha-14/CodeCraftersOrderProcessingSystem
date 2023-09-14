package com.codecrafters.exception;

public class OrderApprovalFailureException extends RuntimeException {
    public OrderApprovalFailureException(String message) {
        super(message);
    }
}
