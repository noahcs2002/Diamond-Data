package com.dd.api.restapi.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "dd_defense", schema = "sp24")
public class DefensivePlayer {
    // Stat count 12

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;
    private List<String> positions;
    private int assists;

    // catchers only
    private double caughtStealingPercent;
    private double stolenBasesAllowed;
    private double stolenBaseAttempts;
    private int doublePlay;
    private int errors;
    private double fieldingPercentage;
    private int inningsPlayed;
    private int outs;
    private int outfieldAssists;
    private int passedBalls;
    private int putouts;
    private int totalChances;
    private int triplePlays;
    private long ghostedDate;

    @JsonCreator
    public DefensivePlayer(List<String> positions,
                           Team team,
                           int assists,
                           double caughtStealingPercent,
                           int doublePlay,
                           int errors,
                           double fieldingPercentage,
                           int inningsPlayed,
                           int outs,
                           int outfieldAssists,
                           int passedBalls,
                           int putouts,
                           int stolenBasesAllowed,
                           int stolenBaseAttempts,
                           int totalChances,
                           int triplePlays) {
        this.positions = positions;
        this.team = team;
        this.assists = assists;
        this.caughtStealingPercent = caughtStealingPercent;
        this.doublePlay = doublePlay;
        this.errors = errors;
        this.fieldingPercentage = fieldingPercentage;
        this.inningsPlayed = inningsPlayed;
        this.outs = outs;
        this.outfieldAssists = outfieldAssists;
        this.passedBalls = passedBalls;
        this.putouts = putouts;
        this.stolenBaseAttempts = stolenBaseAttempts;
        this.stolenBasesAllowed = stolenBasesAllowed;
        this.totalChances = totalChances;
        this.triplePlays = triplePlays;
    }

    public DefensivePlayer() {
        // Empty for Hibernate
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public double getCaughtStealingPercent() {
        return caughtStealingPercent;
    }

    public void setCaughtStealingPercent(double caughtStealingPercent) {
        this.caughtStealingPercent = caughtStealingPercent;
    }

    public int getDoublePlay() {
        return doublePlay;
    }

    public void setDoublePlay(int doublePlay) {
        this.doublePlay = doublePlay;
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

    public List<String> getPositions() {
        return positions;
    }

    public void setPositions(List<String> positions) {
        this.positions = positions;
    }

    public long getGhostedDate() {
        return ghostedDate;
    }

    public void setGhostedDate(long ghostedDate) {
        this.ghostedDate = ghostedDate;
    }

    public double getStolenBasesAllowed() {
        return stolenBasesAllowed;
    }

    public void setStolenBasesAllowed(double stolenBasesAllowed) {
        this.stolenBasesAllowed = stolenBasesAllowed;
    }

    public double getStolenBaseAttempts() {
        return stolenBaseAttempts;
    }

    public void setStolenBaseAttempts(double stolenBaseAttempts) {
        this.stolenBaseAttempts = stolenBaseAttempts;
    }

    @Override
    public String toString() {
        return "DefensivePlayer{" +
                "id=" + id +
                ", team=" + team +
                ", positions=" + positions +
                ", assists=" + assists +
                ", caughtStealingPercent=" + caughtStealingPercent +
                ", doublePlay=" + doublePlay +
                ", errors=" + errors +
                ", fieldingPercentage=" + fieldingPercentage +
                ", inningsPlayed=" + inningsPlayed +
                ", outs=" + outs +
                ", outfieldAssists=" + outfieldAssists +
                ", passedBalls=" + passedBalls +
                ", putouts=" + putouts +
                ", totalChances=" + totalChances +
                ", triplePlays=" + triplePlays +
                ", ghostedDate=" + ghostedDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefensivePlayer player = (DefensivePlayer) o;
        return assists == player.assists && Double.compare(caughtStealingPercent, player.caughtStealingPercent) == 0 && Double.compare(stolenBasesAllowed, player.stolenBasesAllowed) == 0 && Double.compare(stolenBaseAttempts, player.stolenBaseAttempts) == 0 && doublePlay == player.doublePlay && errors == player.errors && Double.compare(fieldingPercentage, player.fieldingPercentage) == 0 && inningsPlayed == player.inningsPlayed && outs == player.outs && outfieldAssists == player.outfieldAssists && passedBalls == player.passedBalls && putouts == player.putouts && totalChances == player.totalChances && triplePlays == player.triplePlays && ghostedDate == player.ghostedDate && Objects.equals(id, player.id) && Objects.equals(team, player.team) && Objects.equals(positions, player.positions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, team, positions, assists, caughtStealingPercent, stolenBasesAllowed, stolenBaseAttempts, doublePlay, errors, fieldingPercentage, inningsPlayed, outs, outfieldAssists, passedBalls, putouts, totalChances, triplePlays, ghostedDate);
    }
}
