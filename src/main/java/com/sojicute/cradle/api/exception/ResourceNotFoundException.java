package com.sojicute.cradle.api.exception;

public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersion = 1L;

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
