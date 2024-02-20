package com.anikulin.we.exception;


public class ValidationException extends IllegalArgumentException {

    public ValidationException(String message) {
        super(message);
    }
}
