package com.tourguide.place.exceptions;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String message) {
        super(message);
    }

    public InvalidDataException(String message, Exception exception) {
        super(message, exception);
    }
}