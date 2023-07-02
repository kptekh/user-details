package com.encrypt.user.exception;

public class UserNotFoundException extends RuntimeException{

    private int code;

    public UserNotFoundException(String message, int code) {
        super(message);
        this.code = code;
    }
}
