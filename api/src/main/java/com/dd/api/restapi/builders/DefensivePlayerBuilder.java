package com.dd.api.restapi.builders;

import com.dd.api.restapi.models.DefensivePlayer;

import java.util.UUID;

public class DefensivePlayerBuilder {
    private UUID id;
    private UUID teamId;
    private UUID memberId;
    private int assists;
    private double caughtStealingPercentage;
    private int doublePlays;
    private int errors;
    private double fieldingPercentage;
    private int inningsPlayed;
    private int outs;
    private int outfieldAssists;
    private int passedBalls;
    private int putouts;
    private int totalChances;
    private int triplePlays;

    public DefensivePlayerBuilder setTeamId(UUID teamId) {
        this.teamId = teamId;
        return this;
    }

    public DefensivePlayerBuilder setMemberId(UUID memberId) {
        this.memberId = memberId;
        return this;
    }

    public DefensivePlayerBuilder setAssists(int assists) {
        this.assists = assists;
        return this;
    }

    public DefensivePlayerBuilder setCaughtStealingPercentage(double caughtStealingPercentage) {
        this.caughtStealingPercentage = caughtStealingPercentage;
        return this;
    }

    public DefensivePlayerBuilder setDoublePlays(int doublePlays) {
        this.doublePlays = doublePlays;
        return this;
    }

    public DefensivePlayerBuilder setErrors(int errors) {
        this.errors = errors;
        return this;
    }

    public DefensivePlayerBuilder setFieldingPercentage(double fieldingPercentage) {
        this.fieldingPercentage = fieldingPercentage;
        return this;
    }

    public DefensivePlayerBuilder setInningsPlayed(int inningsPlayed) {
        this.inningsPlayed = inningsPlayed;
        return this;
    }

    public DefensivePlayerBuilder setOuts(int outs) {
        this.outs = outs;
        return this;
    }

    public DefensivePlayerBuilder setOutfieldAssists(int outfieldAssists) {
        this.outfieldAssists = outfieldAssists;
        return this;
    }

    public DefensivePlayerBuilder setPassedBalls(int passedBalls) {
        this.passedBalls = passedBalls;
        return this;
    }

    public DefensivePlayerBuilder setPutouts(int putouts) {
        this.putouts = putouts;
        return this;
    }

    public DefensivePlayerBuilder setTotalChances(int totalChances) {
        this.totalChances = totalChances;
        return this;
    }

    public DefensivePlayerBuilder setTriplePlays(int triplePlays) {
        this.triplePlays = triplePlays;
        return this;
    }

    public DefensivePlayer createDefensivePlayer() {
        return new DefensivePlayer(teamId, assists, caughtStealingPercentage, doublePlays, errors, fieldingPercentage, inningsPlayed, outs, outfieldAssists, passedBalls, putouts, totalChances, triplePlays);
    }
}