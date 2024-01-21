package com.dd.api.restapi.builders;

import com.dd.api.restapi.models.Pitcher;
import com.dd.api.restapi.util.PitcherPreference;

import java.util.UUID;

public class PitcherBuilder {
    private UUID id;
    private UUID teamId;
    private UUID memberId;
    private int appearances;
    private int balks;
    private int battersFaced;
    private int blownSaves;
    private int completeGames;
    private int earnedRunsAllowed;
    private double earnedRunAverage;
    private int flyOuts;
    private int gamesFinished;
    private int gamesStarted;
    private int groundOuts;
    private int holds;
    private int inheritedRunners;
    private double inningsPitched;
    private int losses;
    private int numberOfPitches;
    private int pickOffs;
    private int qualityStarts;
    private int reliefWins;
    private int saves;
    private int saveOpportunities;
    private double savePercentage;
    private int shutouts;
    private int strikeouts;
    private int unearnedRuns;
    private double walksAndHitsPerInningsPitched;
    private int wildPitches;
    private int wins;
    private double winningPercentage;
    private PitcherPreference preference;

    public PitcherBuilder setPreference(PitcherPreference preference) {
        this.preference = preference;
        return this;
    }

    public PitcherBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public PitcherBuilder setTeamId(UUID teamId) {
        this.teamId = teamId;
        return this;
    }

    public PitcherBuilder setMemberId(UUID memberId) {
        this.memberId = memberId;
        return this;
    }

    public PitcherBuilder setAppearances(int appearances) {
        this.appearances = appearances;
        return this;
    }

    public PitcherBuilder setBalks(int balks) {
        this.balks = balks;
        return this;
    }

    public PitcherBuilder setBattersFaced(int battersFaced) {
        this.battersFaced = battersFaced;
        return this;
    }

    public PitcherBuilder setBlownSaves(int blownSaves) {
        this.blownSaves = blownSaves;
        return this;
    }

    public PitcherBuilder setCompleteGames(int completeGames) {
        this.completeGames = completeGames;
        return this;
    }

    public PitcherBuilder setEarnedRunsAllowed(int earnedRunsAllowed) {
        this.earnedRunsAllowed = earnedRunsAllowed;
        return this;
    }

    public PitcherBuilder setEarnedRunAverage(double earnedRunAverage) {
        this.earnedRunAverage = earnedRunAverage;
        return this;
    }

    public PitcherBuilder setFlyOuts(int flyOuts) {
        this.flyOuts = flyOuts;
        return this;
    }

    public PitcherBuilder setGamesFinished(int gamesFinished) {
        this.gamesFinished = gamesFinished;
        return this;
    }

    public PitcherBuilder setGamesStarted(int gamesStarted) {
        this.gamesStarted = gamesStarted;
        return this;
    }

    public PitcherBuilder setGroundOuts(int groundOuts) {
        this.groundOuts = groundOuts;
        return this;
    }

    public PitcherBuilder setHolds(int holds) {
        this.holds = holds;
        return this;
    }

    public PitcherBuilder setInheritedRunners(int inheritedRunners) {
        this.inheritedRunners = inheritedRunners;
        return this;
    }

    public PitcherBuilder setInningsPitched(double inningsPitched) {
        this.inningsPitched = inningsPitched;
        return this;
    }

    public PitcherBuilder setLosses(int losses) {
        this.losses = losses;
        return this;
    }

    public PitcherBuilder setNumberOfPitches(int numberOfPitches) {
        this.numberOfPitches = numberOfPitches;
        return this;
    }

    public PitcherBuilder setPickOffs(int pickOffs) {
        this.pickOffs = pickOffs;
        return this;
    }

    public PitcherBuilder setQualityStarts(int qualityStarts) {
        this.qualityStarts = qualityStarts;
        return this;
    }

    public PitcherBuilder setReliefWins(int reliefWins) {
        this.reliefWins = reliefWins;
        return this;
    }

    public PitcherBuilder setSaves(int saves) {
        this.saves = saves;
        return this;
    }

    public PitcherBuilder setSaveOpportunities(int saveOpportunities) {
        this.saveOpportunities = saveOpportunities;
        return this;
    }

    public PitcherBuilder setSavePercentage(double savePercentage) {
        this.savePercentage = savePercentage;
        return this;
    }

    public PitcherBuilder setShutouts(int shutouts) {
        this.shutouts = shutouts;
        return this;
    }

    public PitcherBuilder setStrikeouts(int strikeouts) {
        this.strikeouts = strikeouts;
        return this;
    }

    public PitcherBuilder setUnearnedRuns(int unearnedRuns) {
        this.unearnedRuns = unearnedRuns;
        return this;
    }

    public PitcherBuilder setWalksAndHitsPerInningsPitched(double walksAndHitsPerInningsPitched) {
        this.walksAndHitsPerInningsPitched = walksAndHitsPerInningsPitched;
        return this;
    }

    public PitcherBuilder setWildPitches(int wildPitches) {
        this.wildPitches = wildPitches;
        return this;
    }

    public PitcherBuilder setWins(int wins) {
        this.wins = wins;
        return this;
    }

    public PitcherBuilder setWinningPercentage(double winningPercentage) {
        this.winningPercentage = winningPercentage;
        return this;
    }

    public Pitcher createPitcher() {
        return new Pitcher(id, teamId, memberId, appearances, balks, battersFaced, blownSaves, completeGames, earnedRunsAllowed, earnedRunAverage, flyOuts, gamesFinished, gamesStarted, groundOuts, holds, inheritedRunners, inningsPitched, losses, numberOfPitches, pickOffs, qualityStarts, reliefWins, saves, saveOpportunities, savePercentage, shutouts, strikeouts, unearnedRuns, walksAndHitsPerInningsPitched, wildPitches, wins, winningPercentage, preference);
    }
}