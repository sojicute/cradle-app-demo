package com.sojicute.cradle.api.exception;

public class UserNameNotFoundException extends RuntimeException {
    private static final long serialVersion = 1L;

    public UserNameNotFoundException(String msg) {
        super(msg);
    }
}
