package com.dd.api.restapi.builders;

import com.dd.api.restapi.models.OffensivePlayer;
import com.dd.api.restapi.util.BatterPreference;

import java.util.UUID;

public class OffensivePlayerBuilder {
    private UUID id;
    private UUID teamId;
    private UUID memberId;
    private int atBats;
    private double average;
    private double caughtStealingPercentage;
    private int doubles;
    private int extraBaseHits;
    private int gamesPlayed;
    private int grandSlams;
    private int groundIntoDoublePlay;
    private double groundOutVsFlyOut;
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
    private int sacrificeBunts;
    private int sacrificeFlies;
    private int singles;
    private double slugging;
    private int stolenBases;
    private int stolenBaseAttempts;
    private int totalBases;
    private int triples;
    private int walks;
    private int walkOffs;
    private BatterPreference preference;
    private String firstName;
    private String lastName;

    public OffensivePlayerBuilder setId(UUID id) {
        this.id = id;
        return this;
    }
    
    public OffensivePlayerBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }
    
    public OffensivePlayerBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public OffensivePlayerBuilder setTeamId(UUID teamId) {
        this.teamId = teamId;
        return this;
    }

    public OffensivePlayerBuilder setMemberId(UUID memberId) {
        this.memberId = memberId;
        return this;
    }

    public OffensivePlayerBuilder setAtBats(int atBats) {
        this.atBats = atBats;
        return this;
    }

    public OffensivePlayerBuilder setAverage(double average) {
        this.average = average;
        return this;
    }

    public OffensivePlayerBuilder setCaughtStealingPercentage(double caughtStealingPercentage) {
        this.caughtStealingPercentage = caughtStealingPercentage;
        return this;
    }

    public OffensivePlayerBuilder setDoubles(int doubles) {
        this.doubles = doubles;
        return this;
    }

    public OffensivePlayerBuilder setExtraBaseHits(int extraBaseHits) {
        this.extraBaseHits = extraBaseHits;
        return this;
    }

    public OffensivePlayerBuilder setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
        return this;
    }

    public OffensivePlayerBuilder setGrandSlams(int grandSlams) {
        this.grandSlams = grandSlams;
        return this;
    }

    public OffensivePlayerBuilder setGroundIntoDoublePlay(int groundIntoDoublePlay) {
        this.groundIntoDoublePlay = groundIntoDoublePlay;
        return this;
    }

    public OffensivePlayerBuilder setGroundOutVsFlyOut(double groundOutVsFlyOut) {
        this.groundOutVsFlyOut = groundOutVsFlyOut;
        return this;
    }

    public OffensivePlayerBuilder setHitByPitch(int hitByPitch) {
        this.hitByPitch = hitByPitch;
        return this;
    }

    public OffensivePlayerBuilder setHits(int hits) {
        this.hits = hits;
        return this;
    }

    public OffensivePlayerBuilder setHomeRuns(int homeRuns) {
        this.homeRuns = homeRuns;
        return this;
    }

    public OffensivePlayerBuilder setIntentionalWalks(int intentionalWalks) {
        this.intentionalWalks = intentionalWalks;
        return this;
    }

    public OffensivePlayerBuilder setLeftOnBase(int leftOnBase) {
        this.leftOnBase = leftOnBase;
        return this;
    }

    public OffensivePlayerBuilder setOnBasePercentage(double onBasePercentage) {
        this.onBasePercentage = onBasePercentage;
        return this;
    }

    public OffensivePlayerBuilder setOnBasePlusSlugging(double onBasePlusSlugging) {
        this.onBasePlusSlugging = onBasePlusSlugging;
        return this;
    }

    public OffensivePlayerBuilder setPlateAppearances(int plateAppearances) {
        this.plateAppearances = plateAppearances;
        return this;
    }

    public OffensivePlayerBuilder setReachedOnError(int reachedOnError) {
        this.reachedOnError = reachedOnError;
        return this;
    }

    public OffensivePlayerBuilder setRuns(int runs) {
        this.runs = runs;
        return this;
    }

    public OffensivePlayerBuilder setRunsBattedIn(int runsBattedIn) {
        this.runsBattedIn = runsBattedIn;
        return this;
    }

    public OffensivePlayerBuilder setSacrificeBunts(int sacrificeBunts) {
        this.sacrificeBunts = sacrificeBunts;
        return this;
    }

    public OffensivePlayerBuilder setSacrificeFlies(int sacrificeFlies) {
        this.sacrificeFlies = sacrificeFlies;
        return this;
    }

    public OffensivePlayerBuilder setSingles(int singles) {
        this.singles = singles;
        return this;
    }

    public OffensivePlayerBuilder setSlugging(double slugging) {
        this.slugging = slugging;
        return this;
    }

    public OffensivePlayerBuilder setStolenBases(int stolenBases) {
        this.stolenBases = stolenBases;
        return this;
    }

    public OffensivePlayerBuilder setTotalBases(int totalBases) {
        this.totalBases = totalBases;
        return this;
    }

    public OffensivePlayerBuilder setTriples(int triples) {
        this.triples = triples;
        return this;
    }

    public OffensivePlayerBuilder setWalks(int walks) {
        this.walks = walks;
        return this;
    }

    public OffensivePlayerBuilder setWalkOffs(int walkOffs) {
        this.walkOffs = walkOffs;
        return this;
    }

    public OffensivePlayerBuilder setPreference(BatterPreference preference) {
        this.preference = preference;
        return this;
    }
    
    public OffensivePlayerBuilder setStolenBaseAttempts(int stolenBaseAttempts) {
       this.stolenBaseAttempts = stolenBaseAttempts;
       return this;
    }

    public OffensivePlayer createOffensivePlayer() {
        return new OffensivePlayer(id, teamId, memberId, atBats, average, caughtStealingPercentage, doubles, extraBaseHits, gamesPlayed, grandSlams, groundIntoDoublePlay, groundOutVsFlyOut, hitByPitch, hits, homeRuns, intentionalWalks, leftOnBase, onBasePercentage, onBasePlusSlugging, plateAppearances, reachedOnError, runs, runsBattedIn, sacrificeBunts, sacrificeFlies, singles, slugging, stolenBases, stolenBaseAttempts, totalBases, triples, walks, walkOffs, preference, firstName, lastName);
    }
}