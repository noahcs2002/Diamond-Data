package com.dd.api.util.exceptions;

import jakarta.servlet.ServletException;

public class NoAccessPermittedException extends ServletException {

    public NoAccessPermittedException(Long disallowedUserId) {
        super("No access permitted for user: user<"+disallowedUserId+">");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
