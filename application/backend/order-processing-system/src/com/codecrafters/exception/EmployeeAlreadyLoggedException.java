package com.codecrafters.exception;

public class EmployeeAlreadyLoggedException extends RuntimeException {
    public EmployeeAlreadyLoggedException(String message) {
        super(message);
    }
}
