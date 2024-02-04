package com.dd.api.auth.models;

import com.dd.api.util.ann.UtilityConstructor;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;
import java.util.UUID;

public class User {
    private UUID id;
    private String username;
    private String password;
    private UUID accountId;

    @UtilityConstructor(description = "Used for SQL queries")
    public User(UUID id, String username, String password, UUID accountId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.accountId = accountId;
    }

    @JsonCreator
    public User(String username, String password, UUID accountId) {
        this.username = username;
        this.password = password;
        this.accountId = accountId;
        this.id = UUID.randomUUID();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(accountId, user.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, accountId);
    }

    public static User EMPTY_USER() {
        return new User(null, "", "", null);
    }
}
