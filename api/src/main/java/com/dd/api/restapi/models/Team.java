package com.dd.api.restapi.models;

import java.util.Objects;
import java.util.UUID;

public class Team {
    private String name;
    private UUID id;
    private UUID accountId;

    private Color primaryColour;
    private Color secondaryColour;
    private Color accentColour;

    public Team(String name, UUID id, UUID accountId, Color primaryColour, Color secondaryColour, Color accentColour) {
        this.name = name;
        this.id = id;
        this.accountId = accountId;
        this.primaryColour = primaryColour;
        this.secondaryColour = secondaryColour;
        this.accentColour = accentColour;
    }

    public Team(String name, UUID accountId, Color primaryColour, Color secondaryColour, Color accentColour) {
        this.name = name;
        this.accountId = accountId;
        this.primaryColour = primaryColour;
        this.secondaryColour = secondaryColour;
        this.accentColour = accentColour;
    }

    public Team() {
        this.id = UUID.randomUUID();
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

    public Color getPrimaryColour() {
        return primaryColour;
    }

    public void setPrimaryColour(Color primaryColour) {
        this.primaryColour = primaryColour;
    }

    public Color getSecondaryColour() {
        return secondaryColour;
    }

    public void setSecondaryColour(Color secondaryColour) {
        this.secondaryColour = secondaryColour;
    }

    public Color getAccentColour() {
        return accentColour;
    }

    public void setAccentColour(Color accentColour) {
        this.accentColour = accentColour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(name, team.name) && Objects.equals(id, team.id) && Objects.equals(accountId, team.accountId) && Objects.equals(primaryColour, team.primaryColour) && Objects.equals(secondaryColour, team.secondaryColour) && Objects.equals(accentColour, team.accentColour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, accountId, primaryColour, secondaryColour, accentColour);
    }
}
