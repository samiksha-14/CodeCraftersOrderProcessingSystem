package com.codecrafters.exception;

public class OrderDateBeyondThreeWorkingDaysException extends RuntimeException {
    public OrderDateBeyondThreeWorkingDaysException(String message) {
        super(message);
    }
}
