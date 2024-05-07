package com.dd.api.restapi.requestmodels;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Used to simulate an At Bat
 */
public class AtBatResultModel {
    private String result;

    @JsonCreator
    public AtBatResultModel(String result) {
        this.result = result;
    }

    public AtBatResultModel() {
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return this.result;
    }
}
