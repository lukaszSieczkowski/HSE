package com.codedito.userservice.bootstrap;

public enum UsersNames {
    USER("user", "test1@email.com", "pass"),
    ADMINISTRATOR("admin", "test2@email.com", "pass"),
    EXPIRED_CREDENTIALS_USER("expiredCredentials", "test3@email.com", "pass"),
    EXPIRED_ACCOUNT_USER("expiredAccount", "test4@email.com", "pass"),
    LOCKED_ACCOUNT_USER("lockedAccountU", "test5@email.com", "pass"),
    ENABLED_USER("enabledUser", "test6@email.com", "pass");

    private final String name;
    private final String email;
    private final String password;

    UsersNames(final String name, final String email, final String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}