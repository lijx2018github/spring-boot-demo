package com.lijx.demo.common.exception.user;

public class UserNotExistsException extends Exception {

    public UserNotExistsException() {
        super();
    }

    public UserNotExistsException(String message) {
        super(message);
    }
}
