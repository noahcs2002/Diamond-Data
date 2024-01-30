package com.dd.api.restapi.builders;

import com.dd.api.restapi.models.Color;
import com.dd.api.restapi.models.Team;

import java.util.UUID;

public class TeamBuilder {
    private String name;
    private UUID id;
    private UUID accountId;
    private Color primaryColour;
    private Color secondaryColour;
    private Color accentColour;

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

    public TeamBuilder setPrimaryColour(Color colour) {
        this.primaryColour = colour;
        return this;
    }

    public TeamBuilder setSecondaryColour(Color colour) {
        this.secondaryColour = colour;
        return this;
    }

    public TeamBuilder setAccentColour(Color colour) {
        this.accentColour = colour;
        return this;
    }
    public Team createTeam() {
        return new Team(name, id, accountId, primaryColour, secondaryColour, accentColour);
    }
}