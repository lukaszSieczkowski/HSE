package com.codedito.userservice.exception;

import org.springframework.security.core.AuthenticationException;

public class LockedAccountException extends AuthenticationException {

    public LockedAccountException(final String message) {
        super(message);
    }
}
