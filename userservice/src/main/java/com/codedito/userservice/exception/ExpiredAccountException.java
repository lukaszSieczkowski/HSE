package com.codedito.userservice.exception;

import org.springframework.security.core.AuthenticationException;

public class ExpiredAccountException extends AuthenticationException {
    public ExpiredAccountException(final String message) {
        super(message);
    }
}
