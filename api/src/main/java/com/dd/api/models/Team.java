package com.dd.api.models;

import java.util.Objects;
import java.util.UUID;

public class Team {
    private String name;
    private UUID id;
    private UUID accountId;

    public Team(String name, UUID id, UUID accountId) {
        this.name = name;
        this.id = id;
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
        Team team = (Team) o;
        return Objects.equals(name, team.name) && Objects.equals(id, team.id) && Objects.equals(accountId, team.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, accountId);
    }
}
