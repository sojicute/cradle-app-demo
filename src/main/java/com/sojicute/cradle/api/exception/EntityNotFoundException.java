package com.sojicute.cradle.api.exception;

public class EntityNotFoundException extends RuntimeException {
    private static final long serialVersion = 1L;

    public EntityNotFoundException(String msg) {
        super(msg);
    }
}
