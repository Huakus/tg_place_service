package com.tourguide.place.exceptions;

public class DoesntExistsException extends RuntimeException {
    public DoesntExistsException(String message) {
        super(message);
    }
}
