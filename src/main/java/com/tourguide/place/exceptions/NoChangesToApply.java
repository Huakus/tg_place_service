package com.tourguide.place.exceptions;

public class NoChangesToApply extends RuntimeException {
    public NoChangesToApply(String message) {
        super(message);
    }

    public NoChangesToApply(String message, Exception exception) {
        super(message, exception);
    }
}
