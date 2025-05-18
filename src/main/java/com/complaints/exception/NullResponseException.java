package com.complaints.exception;

public class NullResponseException extends RuntimeException {
    public NullResponseException(String message) {
        super(message);
    }
}
