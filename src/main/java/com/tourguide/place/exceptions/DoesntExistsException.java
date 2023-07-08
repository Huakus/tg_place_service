package com.tourguide.place.exceptions;

public class DoesntExistsException extends RuntimeException {
    public DoesntExistsException(String message) {
        super(message);
    }

    public DoesntExistsException(String message, Exception exception) {
        super(message, exception);
    }
}
