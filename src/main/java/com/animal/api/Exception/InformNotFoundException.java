package com.animal.api.Exception;

public class InformNotFoundException extends RuntimeException {
    public InformNotFoundException(Integer id) {
        super("Could not find inform " + id);
    }
}
