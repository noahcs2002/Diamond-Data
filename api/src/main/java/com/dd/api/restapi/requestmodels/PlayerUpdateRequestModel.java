package com.dd.api.restapi.requestmodels;

public record PlayerUpdateRequestModel(Long offensiveId, Long defensiveId,
                                       PlayerManipulationRequestModel manipulationRequestModel) {

    @Override
    public String toString() {
        return "PlayerUpdateRequestModel{" +
                "offensiveId=" + offensiveId +
                ", defensiveId=" + defensiveId +
                ", manipulationRequestModel=" + manipulationRequestModel +
                '}';
    }
}
