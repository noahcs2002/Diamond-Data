package com.dd.api.auth.builders;

import com.dd.api.auth.models.Account;

import java.util.UUID;

public class AccountBuilder {
    private UUID id;
    private int userCount;

    public AccountBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public AccountBuilder setUserCount(int userCount) {
        this.userCount = userCount;
        return this;
    }

    public Account createAccount() {
        return new Account(id, userCount);
    }
}