package com.codecrafters.exception;

public class CannotLogoutBeforeLoginEmployeeException extends RuntimeException {
    public CannotLogoutBeforeLoginEmployeeException(String message) {
        super(message);
    }
}
