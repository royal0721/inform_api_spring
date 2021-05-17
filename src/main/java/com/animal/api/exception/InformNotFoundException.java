package com.animal.api.exception;

public class InformNotFoundException extends RuntimeException {
    public InformNotFoundException(Integer id) {
        super("Could not find inform " + id);
    }
}
