package com.dd.api.builders;

import com.dd.api.models.Team;

import java.util.UUID;

public class TeamBuilder {
    private String name;
    private UUID id;
    private UUID accountId;

    public TeamBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public TeamBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public TeamBuilder setAccountId(UUID accountId) {
        this.accountId = accountId;
        return this;
    }

    public Team createTeam() {
        return new Team(name, id, accountId);
    }
}