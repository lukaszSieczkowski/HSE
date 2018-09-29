package com.codedito.userservice.domain;

import lombok.ToString;

@ToString
public enum Role {
    ADMIN("ROLE_ADMIN"), USER("ROLE_USER");

    private final String value;

    Role(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
