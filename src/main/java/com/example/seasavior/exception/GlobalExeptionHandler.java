package com.example.seasavior.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExeptionHandler {
    

    @ExceptionHandler(ObservationNotFoundException.class)
    public ResponseEntity<?> handleObservationNotFoundException(ObservationNotFoundException ex, WebRequest request) {
        return  new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }





}
