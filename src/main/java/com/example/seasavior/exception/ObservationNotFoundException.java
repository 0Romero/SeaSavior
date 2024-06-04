package com.example.seasavior.exception;

public class ObservationNotFoundException extends RuntimeException {
    public ObservationNotFoundException(Long id) { 
        super("Observation not found: " + id);
    }
}
