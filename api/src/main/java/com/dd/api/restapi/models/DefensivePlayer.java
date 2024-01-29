package com.dd.api.restapi.models;

import java.util.Objects;
import java.util.UUID;

public class DefensivePlayer {
    private UUID id;
    private UUID teamId;
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
    
    public DefensivePlayer() {
        // Empty for Jackson Binding (Spring Boot) - NS
    }

    public DefensivePlayer(UUID teamId, int assists, double caughtStealingPercentage, int doublePlays, int errors, double fieldingPercentage, int inningsPlayed, int outs, int outfieldAssists, int passedBalls, int putouts, int totalChances, int triplePlays) {
        this.id = UUID.randomUUID();
        this.teamId = teamId;
        this.assists = assists;
        this.caughtStealingPercentage = caughtStealingPercentage;
        this.doublePlays = doublePlays;
        this.errors = errors;
        this.fieldingPercentage = fieldingPercentage;
        this.inningsPlayed = inningsPlayed;
        this.outs = outs;
        this.outfieldAssists = outfieldAssists;
        this.passedBalls = passedBalls;
        this.putouts = putouts;
        this.totalChances = totalChances;
        this.triplePlays = triplePlays;
    }
    
    public DefensivePlayer(UUID playerId, UUID teamId, int assists, double caughtStealingPercentage, int doublePlays, int errors, double fieldingPercentage, int inningsPlayed, int outs, int outfieldAssists, int passedBalls, int putouts, int totalChances, int triplePlays) {
        this.id = playerId;
        this.teamId = teamId;
        this.assists = assists;
        this.caughtStealingPercentage = caughtStealingPercentage;
        this.doublePlays = doublePlays;
        this.errors = errors;
        this.fieldingPercentage = fieldingPercentage;
        this.inningsPlayed = inningsPlayed;
        this.outs = outs;
        this.outfieldAssists = outfieldAssists;
        this.passedBalls = passedBalls;
        this.putouts = putouts;
        this.totalChances = totalChances;
        this.triplePlays = triplePlays;
    }
    

    public DefensivePlayer(UUID id, UUID teamId) {
        this.id = id;
        this.teamId = teamId;
        this.assists = 0;
        this.caughtStealingPercentage = 0;
        this.doublePlays = 0;
        this.errors = 0;
        this.fieldingPercentage = 0;
        this.inningsPlayed = 0;
        this.outs = 0;
        this.outfieldAssists = 0;
        this.passedBalls = 0;
        this.putouts = 0;
        this.totalChances = 0;
        this.triplePlays = 0;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getTeamId() {
        return teamId;
    }

    public void setTeamId(UUID teamId) {
        this.teamId = teamId;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public double getCaughtStealingPercentage() {
        return caughtStealingPercentage;
    }

    public void setCaughtStealingPercentage(double caughtStealingPercentage) {
        this.caughtStealingPercentage = caughtStealingPercentage;
    }

    public int getDoublePlays() {
        return doublePlays;
    }

    public void setDoublePlays(int doublePlays) {
        this.doublePlays = doublePlays;
    }

    public int getErrors() {
        return errors;
    }

    public void setErrors(int errors) {
        this.errors = errors;
    }

    public double getFieldingPercentage() {
        return fieldingPercentage;
    }

    public void setFieldingPercentage(double fieldingPercentage) {
        this.fieldingPercentage = fieldingPercentage;
    }

    public int getInningsPlayed() {
        return inningsPlayed;
    }

    public void setInningsPlayed(int inningsPlayed) {
        this.inningsPlayed = inningsPlayed;
    }

    public int getOuts() {
        return outs;
    }

    public void setOuts(int outs) {
        this.outs = outs;
    }

    public int getOutfieldAssists() {
        return outfieldAssists;
    }

    public void setOutfieldAssists(int outfieldAssists) {
        this.outfieldAssists = outfieldAssists;
    }

    public int getPassedBalls() {
        return passedBalls;
    }

    public void setPassedBalls(int passedBalls) {
        this.passedBalls = passedBalls;
    }

    public int getPutouts() {
        return putouts;
    }

    public void setPutouts(int putouts) {
        this.putouts = putouts;
    }

    public int getTotalChances() {
        return totalChances;
    }

    public void setTotalChances(int totalChances) {
        this.totalChances = totalChances;
    }

    public int getTriplePlays() {
        return triplePlays;
    }

    public void setTriplePlays(int triplePlays) {
        this.triplePlays = triplePlays;
    }

    @Override
    public String toString() {
        return "DefensivePlayer{" +
                "id=" + id +
                ", teamId=" + teamId +
                ", assists=" + assists +
                ", caughtStealingPercentage=" + caughtStealingPercentage +
                ", doublePlays=" + doublePlays +
                ", errors=" + errors +
                ", fieldingPercentage=" + fieldingPercentage +
                ", inningsPlayed=" + inningsPlayed +
                ", outs=" + outs +
                ", outfieldAssists=" + outfieldAssists +
                ", passedBalls=" + passedBalls +
                ", putouts=" + putouts +
                ", totalChances=" + totalChances +
                ", triplePlays=" + triplePlays +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefensivePlayer that = (DefensivePlayer) o;
        return assists == that.assists && Double.compare(that.caughtStealingPercentage, caughtStealingPercentage) == 0 && doublePlays == that.doublePlays && errors == that.errors && Double.compare(that.fieldingPercentage, fieldingPercentage) == 0 && inningsPlayed == that.inningsPlayed && outs == that.outs && outfieldAssists == that.outfieldAssists && passedBalls == that.passedBalls && putouts == that.putouts && totalChances == that.totalChances && triplePlays == that.triplePlays && Objects.equals(id, that.id) && Objects.equals(teamId, that.teamId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teamId,  assists, caughtStealingPercentage, doublePlays, errors, fieldingPercentage, inningsPlayed, outs, outfieldAssists, passedBalls, putouts, totalChances, triplePlays);
    }
}
