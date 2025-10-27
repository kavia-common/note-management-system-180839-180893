package com.example.backendapi.exception;

/**
 * Exception indicating a requested resource was not found.
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
