package com.dd.api.util.exceptions;

public class PasswordMismatchException extends Throwable {
    public PasswordMismatchException() {
        super("Passwords do not match");
    }
}
