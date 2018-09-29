package com.codedito.userservice.exception;

import org.springframework.security.core.AuthenticationException;

public class CredentialsExpiredException extends AuthenticationException {

    public CredentialsExpiredException(final String message) {
        super(message);
    }
}
