package com.anikulin.tracker.exception;


public class ValidationException extends IllegalArgumentException {

    public ValidationException(String message) {
        super(message);
    }
}
