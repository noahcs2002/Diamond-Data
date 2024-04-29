package com.dd.api.restapi.requestmodels;

import com.dd.api.restapi.models.Pitcher;
import com.dd.api.restapi.models.Player;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;
import java.util.Objects;

public class EditModel {

    private List<Player> players;
    private List<Pitcher> pitchers;

    private List<Player> playersToDelete;
    private List<Pitcher> pitchersToDelete;

    public EditModel() {
    }

    @JsonCreator
    public EditModel(List<Player> players, List<Pitcher> pitchers, List<Player> playersToDelete, List<Pitcher> pitchersToDelete) {
        this.players = players;
        this.pitchers = pitchers;
        this.playersToDelete = playersToDelete;
        this.pitchersToDelete = pitchersToDelete;
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

    public List<Player> getPlayersToDelete() {
        return playersToDelete;
    }

    public void setPlayersToDelete(List<Player> playersToDelete) {
        this.playersToDelete = playersToDelete;
    }

    public List<Pitcher> getPitchersToDelete() {
        return pitchersToDelete;
    }

    public void setPitchersToDelete(List<Pitcher> pitchersToDelete) {
        this.pitchersToDelete = pitchersToDelete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EditModel editModel = (EditModel) o;
        return Objects.equals(players, editModel.players) && Objects.equals(pitchers, editModel.pitchers) && Objects.equals(playersToDelete, editModel.playersToDelete) && Objects.equals(pitchersToDelete, editModel.pitchersToDelete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(players, pitchers, playersToDelete, pitchersToDelete);
    }

    @Override
    public String toString() {
        return "EditModel{" +
                "players=" + players +
                ", pitchers=" + pitchers +
                ", playersToDelete=" + playersToDelete +
                ", pitchersToDelete=" + pitchersToDelete +
                '}';
    }
}
