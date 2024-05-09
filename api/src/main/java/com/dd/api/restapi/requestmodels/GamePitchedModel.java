package com.dd.api.restapi.requestmodels;

import com.fasterxml.jackson.annotation.JsonCreator;

public class GamePitchedModel {
    private String decision;
    private int pitchCount;
    private double inningsPitched;
    private int walks;
    private int strikeouts;
    private int hits;
    private int earnedRuns;
    private int unearnedRuns;
    private boolean started;

    @JsonCreator
    public GamePitchedModel(String decision, int pitchCount, double inningsPitched, int walks, int strikeouts, int hits, int earnedRuns, int unearnedRuns, boolean started) {
        this.decision = decision;
        this.pitchCount = pitchCount;
        this.inningsPitched = inningsPitched;
        this.walks = walks;
        this.strikeouts = strikeouts;
        this.hits = hits;
        this.earnedRuns = earnedRuns;
        this.unearnedRuns = unearnedRuns;
        this.started = started;
    }



    public GamePitchedModel() {
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public int getPitchCount() {
        return pitchCount;
    }

    public void setPitchCount(int pitchCount) {
        this.pitchCount = pitchCount;
    }

    public double getInningsPitched() {
        return inningsPitched;
    }

    public void setInningsPitched(double inningsPitched) {
        this.inningsPitched = inningsPitched;
    }

    public int getWalks() {
        return walks;
    }

    public void setWalks(int walks) {
        this.walks = walks;
    }

    public int getStrikeouts() {
        return strikeouts;
    }

    public void setStrikeouts(int strikeouts) {
        this.strikeouts = strikeouts;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getEarnedRuns() {
        return earnedRuns;
    }

    public void setEarnedRuns(int earnedRuns) {
        this.earnedRuns = earnedRuns;
    }

    public int getUnearnedRuns() {
        return unearnedRuns;
    }

    public void setUnearnedRuns(int unearnedRuns) {
        this.unearnedRuns = unearnedRuns;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }
}
