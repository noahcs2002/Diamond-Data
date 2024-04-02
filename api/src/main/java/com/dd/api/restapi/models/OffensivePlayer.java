package com.dd.api.restapi.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "dd_offense", schema = "sp24")
public class OffensivePlayer {
    // stat count: 29

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;
    private int atBats;
    private double battingAverage;
    private int caughtStealing;
    private double caughtStealingPercentage;
    private int doubles;
    private int extraBaseHits;
    private int gamesPlayed;
    private int grandSlams;
    private int groundIntoDoublePlay;
    private int hitByPitch;
    private int hits;
    private int homeRuns;
    private int intentionalWalks;
    private int leftOnBase;
    private double onBasePercentage;
    private double onBasePlusSlugging;
    private int plateAppearances;
    private int reachedOnError;
    private int runs;
    private int runsBattedIn;
    private int sacrificeBunt;
    private int sacrificeFly;
    private int singles;
    private double sluggingPercentage;
    private int stolenBases;
    private int totalBases;
    private int triples;
    private int walks;
    private int walkOffs;
    private long ghostedDate;

    @JsonCreator
    public OffensivePlayer(Team team,
                           int atBats,
                           double battingAverage,
                           int caughtStealing,
                           double caughtStealingPercentage,
                           int doubles,
                           int extraBaseHits,
                           int gamesPlayed,
                           int grandSlams,
                           int groundIntoDoublePlay,
                           int hitByPitch,
                           int hits,
                           int homeRuns,
                           int intentionalWalks,
                           int leftOnBase,
                           double onBasePercentage,
                           double onBasePlusSlugging,
                           int plateAppearances,
                           int reachedOnError,
                           int runs,
                           int runsBattedIn,
                           int sacrificeBunt,
                           int sacrificeFly,
                           int singles,
                           double sluggingPercentage,
                           int stolenBases,
                           int totalBases,
                           int triples,
                           int walks,
                           int walkOffs) {
        this.team = team;
        this.atBats = atBats;
        this.battingAverage = battingAverage;
        this.caughtStealing = caughtStealing;
        this.caughtStealingPercentage = caughtStealingPercentage;
        this.doubles = doubles;
        this.extraBaseHits = extraBaseHits;
        this.gamesPlayed = gamesPlayed;
        this.grandSlams = grandSlams;
        this.groundIntoDoublePlay = groundIntoDoublePlay;
        this.hitByPitch = hitByPitch;
        this.hits = hits;
        this.homeRuns = homeRuns;
        this.intentionalWalks = intentionalWalks;
        this.leftOnBase = leftOnBase;
        this.onBasePercentage = onBasePercentage;
        this.onBasePlusSlugging = onBasePlusSlugging;
        this.plateAppearances = plateAppearances;
        this.reachedOnError = reachedOnError;
        this.runs = runs;
        this.runsBattedIn = runsBattedIn;
        this.sacrificeBunt = sacrificeBunt;
        this.sacrificeFly = sacrificeFly;
        this.singles = singles;
        this.sluggingPercentage = sluggingPercentage;
        this.stolenBases = stolenBases;
        this.totalBases = totalBases;
        this.triples = triples;
        this.walks = walks;
        this.walkOffs = walkOffs;
    }

    public OffensivePlayer() {
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

    public int getAtBats() {
        return atBats;
    }

    public void setAtBats(int atBats) {
        this.atBats = atBats;
    }

    public double getBattingAverage() {
        return battingAverage;
    }

    public void setBattingAverage(double battingAverage) {
        this.battingAverage = battingAverage;
    }

    public int getCaughtStealing() {
        return caughtStealing;
    }

    public void setCaughtStealing(int caughtStealing) {
        this.caughtStealing = caughtStealing;
    }

    public int getDoubles() {
        return doubles;
    }

    public void setDoubles(int doubles) {
        this.doubles = doubles;
    }

    public int getExtraBaseHits() {
        return extraBaseHits;
    }

    public void setExtraBaseHits(int extraBaseHits) {
        this.extraBaseHits = extraBaseHits;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getGrandSlams() {
        return grandSlams;
    }

    public void setGrandSlams(int grandSlams) {
        this.grandSlams = grandSlams;
    }

    public int getGroundIntoDoublePlay() {
        return groundIntoDoublePlay;
    }

    public void setGroundIntoDoublePlay(int groundIntoDoublePlay) {
        this.groundIntoDoublePlay = groundIntoDoublePlay;
    }

    public int getHitByPitch() {
        return hitByPitch;
    }

    public void setHitByPitch(int hitByPitch) {
        this.hitByPitch = hitByPitch;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getHomeRuns() {
        return homeRuns;
    }

    public void setHomeRuns(int homeRuns) {
        this.homeRuns = homeRuns;
    }

    public int getIntentionalWalks() {
        return intentionalWalks;
    }

    public void setIntentionalWalks(int intentionalWalks) {
        this.intentionalWalks = intentionalWalks;
    }

    public int getLeftOnBase() {
        return leftOnBase;
    }

    public void setLeftOnBase(int leftOnBase) {
        this.leftOnBase = leftOnBase;
    }

    public double getOnBasePercentage() {
        return onBasePercentage;
    }

    public void setOnBasePercentage(double onBasePercentage) {
        this.onBasePercentage = onBasePercentage;
    }

    public double getOnBasePlusSlugging() {
        return onBasePlusSlugging;
    }

    public void setOnBasePlusSlugging(double onBasePlusSlugging) {
        this.onBasePlusSlugging = onBasePlusSlugging;
    }

    public int getPlateAppearances() {
        return plateAppearances;
    }

    public void setPlateAppearances(int plateAppearances) {
        this.plateAppearances = plateAppearances;
    }

    public int getReachedOnError() {
        return reachedOnError;
    }

    public void setReachedOnError(int reachedOnError) {
        this.reachedOnError = reachedOnError;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public int getRunsBattedIn() {
        return runsBattedIn;
    }

    public void setRunsBattedIn(int runsBattedIn) {
        this.runsBattedIn = runsBattedIn;
    }

    public int getSacrificeBunt() {
        return sacrificeBunt;
    }

    public void setSacrificeBunt(int sacrificeBunt) {
        this.sacrificeBunt = sacrificeBunt;
    }

    public int getSacrificeFly() {
        return sacrificeFly;
    }

    public void setSacrificeFly(int sacrificeFly) {
        this.sacrificeFly = sacrificeFly;
    }

    public int getSingles() {
        return singles;
    }

    public void setSingles(int singles) {
        this.singles = singles;
    }

    public double getSluggingPercentage() {
        return sluggingPercentage;
    }

    public void setSluggingPercentage(double sluggingPercentage) {
        this.sluggingPercentage = sluggingPercentage;
    }

    public int getStolenBases() {
        return stolenBases;
    }

    public void setStolenBases(int stolenBases) {
        this.stolenBases = stolenBases;
    }

    public int getTotalBases() {
        return totalBases;
    }

    public void setTotalBases(int totalBases) {
        this.totalBases = totalBases;
    }

    public int getTriples() {
        return triples;
    }

    public void setTriples(int triples) {
        this.triples = triples;
    }

    public int getWalks() {
        return walks;
    }

    public void setWalks(int walks) {
        this.walks = walks;
    }

    public int getWalkOffs() {
        return walkOffs;
    }

    public void setWalkOffs(int walkOffs) {
        this.walkOffs = walkOffs;
    }

    public long getGhostedDate() {
        return ghostedDate;
    }

    public void setGhostedDate(long ghostedDate) {
        this.ghostedDate = ghostedDate;
    }

    public double getCaughtStealingPercentage() {
        return caughtStealingPercentage;
    }

    public void setCaughtStealingPercentage(double caughtStealingPercentage) {
        this.caughtStealingPercentage = caughtStealingPercentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OffensivePlayer player = (OffensivePlayer) o;
        return atBats == player.atBats && Double.compare(battingAverage, player.battingAverage) == 0 && caughtStealing == player.caughtStealing && Double.compare(caughtStealingPercentage, player.caughtStealingPercentage) == 0 && doubles == player.doubles && extraBaseHits == player.extraBaseHits && gamesPlayed == player.gamesPlayed && grandSlams == player.grandSlams && groundIntoDoublePlay == player.groundIntoDoublePlay && hitByPitch == player.hitByPitch && hits == player.hits && homeRuns == player.homeRuns && intentionalWalks == player.intentionalWalks && leftOnBase == player.leftOnBase && Double.compare(onBasePercentage, player.onBasePercentage) == 0 && Double.compare(onBasePlusSlugging, player.onBasePlusSlugging) == 0 && plateAppearances == player.plateAppearances && reachedOnError == player.reachedOnError && runs == player.runs && runsBattedIn == player.runsBattedIn && sacrificeBunt == player.sacrificeBunt && sacrificeFly == player.sacrificeFly && singles == player.singles && Double.compare(sluggingPercentage, player.sluggingPercentage) == 0 && stolenBases == player.stolenBases && totalBases == player.totalBases && triples == player.triples && walks == player.walks && walkOffs == player.walkOffs && ghostedDate == player.ghostedDate && Objects.equals(id, player.id) && Objects.equals(team, player.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, team, atBats, battingAverage, caughtStealing, caughtStealingPercentage, doubles, extraBaseHits, gamesPlayed, grandSlams, groundIntoDoublePlay, hitByPitch, hits, homeRuns, intentionalWalks, leftOnBase, onBasePercentage, onBasePlusSlugging, plateAppearances, reachedOnError, runs, runsBattedIn, sacrificeBunt, sacrificeFly, singles, sluggingPercentage, stolenBases, totalBases, triples, walks, walkOffs, ghostedDate);
    }
}