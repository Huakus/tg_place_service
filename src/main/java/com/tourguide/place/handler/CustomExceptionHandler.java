package com.tourguide.place.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tourguide.place.exceptions.AlreadyExistsException;
import com.tourguide.place.exceptions.DoesntExistsException;
import com.tourguide.place.exceptions.NoChangesToApply;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Object> handleAlreadyExistsException(AlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DoesntExistsException.class)
    public ResponseEntity<Object> handleDoesntExistsException(DoesntExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoChangesToApply.class)
    public ResponseEntity<Object> handleNoChangesToApply(NoChangesToApply ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_MODIFIED);
    }
}
