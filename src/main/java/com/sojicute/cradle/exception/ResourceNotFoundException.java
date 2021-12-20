package com.sojicute.cradle.exception;

public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersion = 1L;

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
