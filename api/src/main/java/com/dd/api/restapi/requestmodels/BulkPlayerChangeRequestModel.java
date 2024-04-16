package com.dd.api.restapi.requestmodels;

import com.dd.api.restapi.models.Pitcher;
import com.dd.api.restapi.models.Player;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;
import java.util.Objects;

public class BulkPlayerChangeRequestModel {
    private List<Player> players;
    private List<Pitcher> pitchers;

    @JsonCreator
    public BulkPlayerChangeRequestModel(List<Player> players, List<Pitcher> pitchers) {
        this.players = players;
        this.pitchers = pitchers;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Pitcher> getPitchers() {
        return pitchers;
    }

    public void setPitchers(List<Pitcher> pitchers) {
        this.pitchers = pitchers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BulkPlayerChangeRequestModel that = (BulkPlayerChangeRequestModel) o;
        return Objects.equals(players, that.players) && Objects.equals(pitchers, that.pitchers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(players, pitchers);
    }

    @Override
    public String toString() {
        return "BulkPlayerChangeRequestModel{" +
                "players=" + players +
                ", pitchers=" + pitchers +
                '}';
    }
}
