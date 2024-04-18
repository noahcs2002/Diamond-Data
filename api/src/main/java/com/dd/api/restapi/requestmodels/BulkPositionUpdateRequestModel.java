package com.dd.api.restapi.requestmodels;

import com.dd.api.restapi.models.Pitcher;
import com.dd.api.restapi.models.Player;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;
import java.util.Objects;

public class BulkPositionUpdateRequestModel {

   private List<TruncatedPlayerModel> models;

    @JsonCreator
    public BulkPositionUpdateRequestModel(List<TruncatedPlayerModel> models) {
        this.models = models;
    }

    public List<TruncatedPlayerModel> getModels() {
        return models;
    }

    public void setModels(List<TruncatedPlayerModel> models) {
        this.models = models;
    }


}