package com.dd.api.auth.models;

import com.dd.api.util.ann.UtilityConstructor;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;
import java.util.UUID;

public class Account {

    private UUID id;
    private int userCount;

    @UtilityConstructor(description = "Used in SQL query building")
    public Account(UUID id, int userCount) {
        this.id = id;
        this.userCount = userCount;
    }

    @JsonCreator
    public Account(int userCount) {
        this.id = UUID.randomUUID();
        this.userCount = userCount;
    }

    @UtilityConstructor(description = "Used for generating an empty account")
    public Account() {
        this.id = UUID.randomUUID();
        this.userCount = 0;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return userCount == account.userCount && Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userCount);
    }
}