package com.dd.api.restapi.requestmodels;

import com.dd.api.restapi.models.Pitcher;
import com.dd.api.restapi.models.Player;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;
import java.util.Objects;

public class DeleteRequestModel {
    private List<Pitcher> pitchersToDelete;
    private List<Player> playersToDelete;

    @JsonCreator
    public DeleteRequestModel() {
    }

    public List<Pitcher> getPitchersToDelete() {
        return pitchersToDelete;
    }

    public void setPitchersToDelete(List<Pitcher> pitchersToDelete) {
        this.pitchersToDelete = pitchersToDelete;
    }

    public List<Player> getPlayersToDelete() {
        return playersToDelete;
    }

    public void setPlayersToDelete(List<Player> playersToDelete) {
        this.playersToDelete = playersToDelete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteRequestModel that = (DeleteRequestModel) o;
        return Objects.equals(pitchersToDelete, that.pitchersToDelete) && Objects.equals(playersToDelete, that.playersToDelete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pitchersToDelete, playersToDelete);
    }

    @Override
    public String toString() {
        return "DeleteRequestModel{" +
                "pitchersToDelete=" + pitchersToDelete +
                ", playersToDelete=" + playersToDelete +
                '}';
    }
}
