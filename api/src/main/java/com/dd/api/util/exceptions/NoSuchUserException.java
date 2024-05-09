package com.dd.api.util.exceptions;

public class NoSuchUserException extends Throwable {
    public NoSuchUserException(Long userId) {
        super("No such user with the id " + userId + " found");
    }
}
