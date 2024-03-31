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
    private int doubles;
    private int extraBaseHits;
    private int gamesPlayed;
    private int grandSlams;
    private int groundIntoDoublePlay;
    private double groundOutAirOut;
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

    public OffensivePlayer(Long id,
                           Team team,
                           int atBats,
                           double battingAverage,
                           int caughtStealing,
                           int doubles,
                           int extraBaseHits,
                           int gamesPlayed,
                           int grandSlams,
                           int groundIntoDoublePlay,
                           double groundOutAirOut,
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
        this.id = id;
        this.team = team;
        this.atBats = atBats;
        this.battingAverage = battingAverage;
        this.caughtStealing = caughtStealing;
        this.doubles = doubles;
        this.extraBaseHits = extraBaseHits;
        this.gamesPlayed = gamesPlayed;
        this.grandSlams = grandSlams;
        this.groundIntoDoublePlay = groundIntoDoublePlay;
        this.groundOutAirOut = groundOutAirOut;
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
        this.ghostedDate = 0;
    }

    @JsonCreator
    public OffensivePlayer(Team team,
                           int atBats,
                           double battingAverage,
                           int caughtStealing,
                           int doubles,
                           int extraBaseHits,
                           int gamesPlayed,
                           int grandSlams,
                           int groundIntoDoublePlay,
                           double groundOutAirOut,
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
        this.doubles = doubles;
        this.extraBaseHits = extraBaseHits;
        this.gamesPlayed = gamesPlayed;
        this.grandSlams = grandSlams;
        this.groundIntoDoublePlay = groundIntoDoublePlay;
        this.groundOutAirOut = groundOutAirOut;
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

    public double getGroundOutAirOut() {
        return groundOutAirOut;
    }

    public void setGroundOutAirOut(double groundOutAirOut) {
        this.groundOutAirOut = groundOutAirOut;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OffensivePlayer that = (OffensivePlayer) o;
        return atBats == that.atBats && Double.compare(battingAverage, that.battingAverage) == 0 && caughtStealing == that.caughtStealing && doubles == that.doubles && extraBaseHits == that.extraBaseHits && gamesPlayed == that.gamesPlayed && grandSlams == that.grandSlams && groundIntoDoublePlay == that.groundIntoDoublePlay && Double.compare(groundOutAirOut, that.groundOutAirOut) == 0 && hitByPitch == that.hitByPitch && hits == that.hits && homeRuns == that.homeRuns && intentionalWalks == that.intentionalWalks && leftOnBase == that.leftOnBase && Double.compare(onBasePercentage, that.onBasePercentage) == 0 && Double.compare(onBasePlusSlugging, that.onBasePlusSlugging) == 0 && plateAppearances == that.plateAppearances && reachedOnError == that.reachedOnError && runs == that.runs && runsBattedIn == that.runsBattedIn && sacrificeBunt == that.sacrificeBunt && sacrificeFly == that.sacrificeFly && singles == that.singles && Double.compare(sluggingPercentage, that.sluggingPercentage) == 0 && stolenBases == that.stolenBases && totalBases == that.totalBases && triples == that.triples && walks == that.walks && walkOffs == that.walkOffs && ghostedDate == that.ghostedDate && Objects.equals(id, that.id) && Objects.equals(team, that.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, team, atBats, battingAverage, caughtStealing, doubles, extraBaseHits, gamesPlayed, grandSlams, groundIntoDoublePlay, groundOutAirOut, hitByPitch, hits, homeRuns, intentionalWalks, leftOnBase, onBasePercentage, onBasePlusSlugging, plateAppearances, reachedOnError, runs, runsBattedIn, sacrificeBunt, sacrificeFly, singles, sluggingPercentage, stolenBases, totalBases, triples, walks, walkOffs, ghostedDate);
    }
}