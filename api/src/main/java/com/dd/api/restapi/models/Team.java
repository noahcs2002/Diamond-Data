package com.dd.api.restapi.models;

import java.util.Objects;
import java.util.UUID;

public class Team {
    private String name;
    private UUID id;
    private UUID accountId;
    private Color primaryColor;
    private Color secondaryColor;
    private Color accentColor;

    public Team(UUID accountId, String name, Color primaryColour, Color secondaryColour, Color accentColour) {
        this.name = name;
        this.accountId = accountId;
        this.primaryColor = primaryColour;
        this.secondaryColor = secondaryColour;
        this.accentColor = accentColour;
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

    public Color getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(Color primaryColor) {
        this.primaryColor = primaryColor;
    }

    public Color getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(Color secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public Color getAccentColor() {
        return accentColor;
    }

    public void setAccentColor(Color accentColor) {
        this.accentColor = accentColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(name, team.name) && Objects.equals(id, team.id) && Objects.equals(accountId, team.accountId) && Objects.equals(primaryColor, team.primaryColor) && Objects.equals(secondaryColor, team.secondaryColor) && Objects.equals(accentColor, team.accentColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, accountId, primaryColor, secondaryColor, accentColor);
    }
}
