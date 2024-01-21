package com.dd.api.models;

import com.dd.api.util.BatterPreference;
import com.dd.api.util.PitcherPreference;

import java.util.Objects;
import java.util.UUID;

public class OffensivePlayer {
    private UUID id;
    private UUID teamId;
    private UUID memberId;
    private int atBats ;
    private double average  ;
    private double caughtStealingPercentage  ;
    private int doubles ;
    private int extraBaseHits ;
    private int gamesPlayed ;
    private int grandSlams ;
    private int groundIntoDoublePlay ;
    private double groundOutVsFlyOut  ;
    private int hitByPitch ;
    private int hits ;
    private int homeRuns ;
    private int intentionalWalks ;
    private int leftOnBase ;
    private double onBasePercentage  ;
    private double onBasePlusSlugging  ;
    private int plateAppearances ;
    private int reachedOnError ;
    private int runs ;
    private int runsBattedIn ;
    private int sacrificeBunts ;
    private int sacrificeFlies ;
    private int singles ;
    private double slugging  ;
    private int stolenBases ;
    private int totalBases ;
    private int triples ;
    private int walks ;
    private int walkOffs ;
    private BatterPreference preference;

    public OffensivePlayer(UUID id, UUID teamId, UUID memberId, int atBats, double average, double caughtStealingPercentage, int doubles, int extraBaseHits, int gamesPlayed, int grandSlams, int groundIntoDoublePlay, double groundOutVsFlyOut, int hitByPitch, int hits, int homeRuns, int intentionalWalks, int leftOnBase, double onBasePercentage, double onBasePlusSlugging, int plateAppearances, int reachedOnError, int runs, int runsBattedIn, int sacrificeBunts, int sacrificeFlies, int singles, double slugging, int stolenBases, int totalBases, int triples, int walks, int walkOffs, BatterPreference preference) {
        this.id = id;
        this.teamId = teamId;
        this.memberId = memberId;
        this.atBats = atBats;
        this.average = average;
        this.caughtStealingPercentage = caughtStealingPercentage;
        this.doubles = doubles;
        this.extraBaseHits = extraBaseHits;
        this.gamesPlayed = gamesPlayed;
        this.grandSlams = grandSlams;
        this.groundIntoDoublePlay = groundIntoDoublePlay;
        this.groundOutVsFlyOut = groundOutVsFlyOut;
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
        this.sacrificeBunts = sacrificeBunts;
        this.sacrificeFlies = sacrificeFlies;
        this.singles = singles;
        this.slugging = slugging;
        this.stolenBases = stolenBases;
        this.totalBases = totalBases;
        this.triples = triples;
        this.walks = walks;
        this.walkOffs = walkOffs;
        this.preference = preference;
    }

    public OffensivePlayer(UUID id, UUID teamId, UUID memberId) {
        this.id = id;
        this.teamId = teamId;
        this.memberId = memberId;
        this.atBats = 0;
        this.average = 0;
        this.caughtStealingPercentage = 0;
        this.doubles = 0;
        this.extraBaseHits = 0;
        this.gamesPlayed = 0;
        this.grandSlams = 0;
        this.groundIntoDoublePlay = 0;
        this.groundOutVsFlyOut = 0;
        this.hitByPitch = 0;
        this.hits = 0;
        this.homeRuns = 0;
        this.intentionalWalks = 0;
        this.leftOnBase = 0;
        this.onBasePercentage = 0;
        this.onBasePlusSlugging = 0;
        this.plateAppearances = 0;
        this.reachedOnError = 0;
        this.runs = 0;
        this.runsBattedIn = 0;
        this.sacrificeBunts = 0;
        this.sacrificeFlies = 0;
        this.singles = 0;
        this.slugging = 0;
        this.stolenBases = 0;
        this.totalBases = 0;
        this.triples = 0;
        this.walks = 0;
        this.walkOffs = 0;
        this.preference = BatterPreference.NONE;
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

    public UUID getMemberId() {
        return memberId;
    }

    public void setMemberId(UUID memberId) {
        this.memberId = memberId;
    }

    public int getAtBats() {
        return atBats;
    }

    public void setAtBats(int atBats) {
        this.atBats = atBats;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public double getCaughtStealingPercentage() {
        return caughtStealingPercentage;
    }

    public void setCaughtStealingPercentage(double caughtStealingPercentage) {
        this.caughtStealingPercentage = caughtStealingPercentage;
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

    public double getGroundOutVsFlyOut() {
        return groundOutVsFlyOut;
    }

    public void setGroundOutVsFlyOut(double groundOutVsFlyOut) {
        this.groundOutVsFlyOut = groundOutVsFlyOut;
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

    public int getSacrificeBunts() {
        return sacrificeBunts;
    }

    public void setSacrificeBunts(int sacrificeBunts) {
        this.sacrificeBunts = sacrificeBunts;
    }

    public int getSacrificeFlies() {
        return sacrificeFlies;
    }

    public void setSacrificeFlies(int sacrificeFlies) {
        this.sacrificeFlies = sacrificeFlies;
    }

    public int getSingles() {
        return singles;
    }

    public void setSingles(int singles) {
        this.singles = singles;
    }

    public double getSlugging() {
        return slugging;
    }

    public void setSlugging(double slugging) {
        this.slugging = slugging;
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

    public BatterPreference getPreference() {
        return preference;
    }

    public void setPreference(BatterPreference preference) {
        this.preference = preference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OffensivePlayer that = (OffensivePlayer) o;
        return atBats == that.atBats && Double.compare(that.average, average) == 0 && Double.compare(that.caughtStealingPercentage, caughtStealingPercentage) == 0 && doubles == that.doubles && extraBaseHits == that.extraBaseHits && gamesPlayed == that.gamesPlayed && grandSlams == that.grandSlams && groundIntoDoublePlay == that.groundIntoDoublePlay && Double.compare(that.groundOutVsFlyOut, groundOutVsFlyOut) == 0 && hitByPitch == that.hitByPitch && hits == that.hits && homeRuns == that.homeRuns && intentionalWalks == that.intentionalWalks && leftOnBase == that.leftOnBase && Double.compare(that.onBasePercentage, onBasePercentage) == 0 && Double.compare(that.onBasePlusSlugging, onBasePlusSlugging) == 0 && plateAppearances == that.plateAppearances && reachedOnError == that.reachedOnError && runs == that.runs && runsBattedIn == that.runsBattedIn && sacrificeBunts == that.sacrificeBunts && sacrificeFlies == that.sacrificeFlies && singles == that.singles && Double.compare(that.slugging, slugging) == 0 && stolenBases == that.stolenBases && totalBases == that.totalBases && triples == that.triples && walks == that.walks && walkOffs == that.walkOffs && Objects.equals(id, that.id) && Objects.equals(teamId, that.teamId) && Objects.equals(memberId, that.memberId) && preference == that.preference;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teamId, memberId, atBats, average, caughtStealingPercentage, doubles, extraBaseHits, gamesPlayed, grandSlams, groundIntoDoublePlay, groundOutVsFlyOut, hitByPitch, hits, homeRuns, intentionalWalks, leftOnBase, onBasePercentage, onBasePlusSlugging, plateAppearances, reachedOnError, runs, runsBattedIn, sacrificeBunts, sacrificeFlies, singles, slugging, stolenBases, totalBases, triples, walks, walkOffs, preference);
    }
}
