package com.dd.api.auth.providers;

import com.dd.api.auth.models.User;

import java.util.UUID;

public class UserBuilder {
    private UUID id;
    private String username;
    private String passwordHash;
    private UUID accountId;

    public UserBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public UserBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
        return this;
    }

    public UserBuilder setAccountId(UUID accountId) {
        this.accountId = accountId;
        return this;
    }

    public User createUser() {
        return new User(id, username, passwordHash, accountId);
    }
}