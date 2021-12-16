package com.sojicute.cradle.api.exception;

public class InvalidTokenException extends RuntimeException {
    private static final long serialVersion = 1L;

    public InvalidTokenException(String msg) {
        super(msg);
    }
}