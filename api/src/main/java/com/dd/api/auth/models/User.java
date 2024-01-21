package com.dd.api.auth.models;

import java.util.Objects;
import java.util.UUID;

public class User {
    private UUID id;
    private String username;
    private String passwordHash;
    private UUID accountId;

    public User(UUID id, String username, String passwordHash, UUID accountId) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.accountId = accountId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(passwordHash, user.passwordHash) && Objects.equals(accountId, user.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, passwordHash, accountId);
    }

    public static User EMPTY_USER() {
        return new User(null, "", "", null);
    }
}
