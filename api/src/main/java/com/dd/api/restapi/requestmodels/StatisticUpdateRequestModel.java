package com.dd.api.restapi.requestmodels;

import com.dd.api.restapi.models.DefensivePlayer;
import com.dd.api.restapi.models.OffensivePlayer;
import com.dd.api.restapi.models.Pitcher;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;

public class StatisticUpdateRequestModel {

    private List<Pitcher> newPitchers;
    private List<OffensivePlayer> newOffensivePlayers;
    private List<DefensivePlayer> newDefensivePlayers;

    @JsonCreator
    public StatisticUpdateRequestModel(List<Pitcher> newPitchers, List<OffensivePlayer> newOffensivePlayers, List<DefensivePlayer> newDefensivePlayers) {
        this.newPitchers = newPitchers;
        this.newOffensivePlayers = newOffensivePlayers;
        this.newDefensivePlayers = newDefensivePlayers;
    }

    public List<Pitcher> getNewPitchers() {
        return newPitchers;
    }

    public void setNewPitchers(List<Pitcher> newPitchers) {
        this.newPitchers = newPitchers;
    }

    public List<OffensivePlayer> getNewOffensivePlayers() {
        return newOffensivePlayers;
    }

    public void setNewOffensivePlayers(List<OffensivePlayer> newOffensivePlayers) {
        this.newOffensivePlayers = newOffensivePlayers;
    }

    public List<DefensivePlayer> getNewDefensivePlayers() {
        return newDefensivePlayers;
    }

    public void setNewDefensivePlayers(List<DefensivePlayer> newDefensivePlayers) {
        this.newDefensivePlayers = newDefensivePlayers;
    }

    @Override
    public String toString() {
        return "StatisticUpdateRequestModel{" +
                "newPitchers=" + newPitchers +
                ", newOffensivePlayers=" + newOffensivePlayers +
                ", newDefensivePlayers=" + newDefensivePlayers +
                '}';
    }
}