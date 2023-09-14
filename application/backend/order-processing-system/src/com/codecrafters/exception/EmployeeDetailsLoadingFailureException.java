package com.codecrafters.exception;

public class EmployeeDetailsLoadingFailureException extends RuntimeException {
    public EmployeeDetailsLoadingFailureException(String message) {
        super(message);
    }
}
