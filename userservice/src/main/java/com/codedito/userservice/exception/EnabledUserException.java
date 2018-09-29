package com.codedito.userservice.exception;

import org.springframework.security.core.AuthenticationException;

public class EnabledUserException extends AuthenticationException {

    public EnabledUserException(final String message) {
        super(message);
    }
}
