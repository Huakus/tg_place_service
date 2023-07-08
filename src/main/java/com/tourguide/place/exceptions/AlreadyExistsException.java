package com.tourguide.place.exceptions;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message) {
        super(message);
    }

    public AlreadyExistsException(String message, Exception exception) {
        super(message, exception);
    }
}