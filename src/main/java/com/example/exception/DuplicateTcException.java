package com.example.exception;

public class DuplicateTcException extends RuntimeException {

    public DuplicateTcException(String message) {
        super(message);
    }
}
