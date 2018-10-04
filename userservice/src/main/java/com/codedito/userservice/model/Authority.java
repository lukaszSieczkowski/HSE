package com.codedito.userservice.model;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {
    private final String authority;

    public Authority(final String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}