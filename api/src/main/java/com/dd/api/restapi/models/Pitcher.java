package com.dd.api.restapi.models;

import com.dd.api.restapi.util.PitcherPreference;

import java.util.Objects;
import java.util.UUID;

public class Pitcher {

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

    public Pitcher(UUID teamId, UUID memberId, int appearances,  int balks, int battersFaced, int blownSaves, int completeGames, int earnedRunsAllowed, double earnedRunAverage, int flyOuts, int gamesFinished, int gamesStarted, int groundOuts, int holds, int inheritedRunners, double inningsPitched, int losses, int numberOfPitches, int pickOffs, int qualityStarts, int reliefWins, int saves, int saveOpportunities, double savePercentage, int shutouts, int strikeouts, int unearnedRuns, double walksAndHitsPerInningsPitched, int wildPitches, int wins, double winningPercentage, PitcherPreference preference) {
        this.id = UUID.randomUUID();
        this.teamId = teamId;
        this.memberId = memberId;
        this.appearances = appearances;
        this.balks = balks;
        this.battersFaced = battersFaced;
        this.blownSaves = blownSaves;
        this.completeGames = completeGames;
        this.earnedRunsAllowed = earnedRunsAllowed;
        this.earnedRunAverage = earnedRunAverage;
        this.flyOuts = flyOuts;
        this.gamesFinished = gamesFinished;
        this.gamesStarted = gamesStarted;
        this.groundOuts = groundOuts;
        this.holds = holds;
        this.inheritedRunners = inheritedRunners;
        this.inningsPitched = inningsPitched;
        this.losses = losses;
        this.numberOfPitches = numberOfPitches;
        this.pickOffs = pickOffs;
        this.qualityStarts = qualityStarts;
        this.reliefWins = reliefWins;
        this.saves = saves;
        this.saveOpportunities = saveOpportunities;
        this.savePercentage = savePercentage;
        this.shutouts = shutouts;
        this.strikeouts = strikeouts;
        this.unearnedRuns = unearnedRuns;
        this.walksAndHitsPerInningsPitched = walksAndHitsPerInningsPitched;
        this.wildPitches = wildPitches;
        this.wins = wins;
        this.winningPercentage = winningPercentage;
        this.preference = preference;
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

    public int getAppearances() {
        return appearances;
    }

    public void setAppearances(int appearances) {
        this.appearances = appearances;
    }

    public int getBalks() {
        return balks;
    }

    public void setBalks(int balks) {
        this.balks = balks;
    }

    public int getBattersFaced() {
        return battersFaced;
    }

    public void setBattersFaced(int battersFaced) {
        this.battersFaced = battersFaced;
    }

    public int getBlownSaves() {
        return blownSaves;
    }

    public void setBlownSaves(int blownSaves) {
        this.blownSaves = blownSaves;
    }

    public int getCompleteGames() {
        return completeGames;
    }

    public void setCompleteGames(int completeGames) {
        this.completeGames = completeGames;
    }

    public int getEarnedRunsAllowed() {
        return earnedRunsAllowed;
    }

    public void setEarnedRunsAllowed(int earnedRunsAllowed) {
        this.earnedRunsAllowed = earnedRunsAllowed;
    }

    public double getEarnedRunAverage() {
        return earnedRunAverage;
    }

    public void setEarnedRunAverage(double earnedRunAverage) {
        this.earnedRunAverage = earnedRunAverage;
    }

    public int getFlyOuts() {
        return flyOuts;
    }

    public void setFlyOuts(int flyOuts) {
        this.flyOuts = flyOuts;
    }

    public int getGamesFinished() {
        return gamesFinished;
    }

    public void setGamesFinished(int gamesFinished) {
        this.gamesFinished = gamesFinished;
    }

    public int getGamesStarted() {
        return gamesStarted;
    }

    public void setGamesStarted(int gamesStarted) {
        this.gamesStarted = gamesStarted;
    }

    public int getGroundOuts() {
        return groundOuts;
    }

    public void setGroundOuts(int groundOuts) {
        this.groundOuts = groundOuts;
    }

    public int getHolds() {
        return holds;
    }

    public void setHolds(int holds) {
        this.holds = holds;
    }

    public int getInheritedRunners() {
        return inheritedRunners;
    }

    public void setInheritedRunners(int inheritedRunners) {
        this.inheritedRunners = inheritedRunners;
    }

    public double getInningsPitched() {
        return inningsPitched;
    }

    public void setInningsPitched(double inningsPitched) {
        this.inningsPitched = inningsPitched;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getNumberOfPitches() {
        return numberOfPitches;
    }

    public void setNumberOfPitches(int numberOfPitches) {
        this.numberOfPitches = numberOfPitches;
    }

    public int getPickOffs() {
        return pickOffs;
    }

    public void setPickOffs(int pickOffs) {
        this.pickOffs = pickOffs;
    }

    public int getQualityStarts() {
        return qualityStarts;
    }

    public void setQualityStarts(int qualityStarts) {
        this.qualityStarts = qualityStarts;
    }

    public int getReliefWins() {
        return reliefWins;
    }

    public void setReliefWins(int reliefWins) {
        this.reliefWins = reliefWins;
    }

    public int getSaves() {
        return saves;
    }

    public void setSaves(int saves) {
        this.saves = saves;
    }

    public int getSaveOpportunities() {
        return saveOpportunities;
    }

    public void setSaveOpportunities(int saveOpportunities) {
        this.saveOpportunities = saveOpportunities;
    }

    public double getSavePercentage() {
        return savePercentage;
    }

    public void setSavePercentage(double savePercentage) {
        this.savePercentage = savePercentage;
    }

    public int getShutouts() {
        return shutouts;
    }

    public void setShutouts(int shutouts) {
        this.shutouts = shutouts;
    }

    public int getStrikeouts() {
        return strikeouts;
    }

    public void setStrikeouts(int strikeouts) {
        this.strikeouts = strikeouts;
    }

    public int getUnearnedRuns() {
        return unearnedRuns;
    }

    public void setUnearnedRuns(int unearnedRuns) {
        this.unearnedRuns = unearnedRuns;
    }

    public double getWalksAndHitsPerInningsPitched() {
        return walksAndHitsPerInningsPitched;
    }

    public void setWalksAndHitsPerInningsPitched(double walksAndHitsPerInningsPitched) {
        this.walksAndHitsPerInningsPitched = walksAndHitsPerInningsPitched;
    }

    public int getWildPitches() {
        return wildPitches;
    }

    public void setWildPitches(int wildPitches) {
        this.wildPitches = wildPitches;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public double getWinningPercentage() {
        return winningPercentage;
    }

    public void setWinningPercentage(double winningPercentage) {
        this.winningPercentage = winningPercentage;
    }

    public PitcherPreference getPreference() {
        return preference;
    }

    public void setPreference(PitcherPreference preference) {
        this.preference = preference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pitcher pitcher = (Pitcher) o;
        return appearances == pitcher.appearances && balks == pitcher.balks && battersFaced == pitcher.battersFaced && blownSaves == pitcher.blownSaves && completeGames == pitcher.completeGames && earnedRunsAllowed == pitcher.earnedRunsAllowed && Double.compare(pitcher.earnedRunAverage, earnedRunAverage) == 0 && flyOuts == pitcher.flyOuts && gamesFinished == pitcher.gamesFinished && gamesStarted == pitcher.gamesStarted && groundOuts == pitcher.groundOuts && holds == pitcher.holds && inheritedRunners == pitcher.inheritedRunners && Double.compare(pitcher.inningsPitched, inningsPitched) == 0 && losses == pitcher.losses && numberOfPitches == pitcher.numberOfPitches && pickOffs == pitcher.pickOffs && qualityStarts == pitcher.qualityStarts && reliefWins == pitcher.reliefWins && saves == pitcher.saves && saveOpportunities == pitcher.saveOpportunities && Double.compare(pitcher.savePercentage, savePercentage) == 0 && shutouts == pitcher.shutouts && strikeouts == pitcher.strikeouts && unearnedRuns == pitcher.unearnedRuns && Double.compare(pitcher.walksAndHitsPerInningsPitched, walksAndHitsPerInningsPitched) == 0 && wildPitches == pitcher.wildPitches && wins == pitcher.wins && Double.compare(pitcher.winningPercentage, winningPercentage) == 0 && Objects.equals(id, pitcher.id) && Objects.equals(teamId, pitcher.teamId) && Objects.equals(memberId, pitcher.memberId) && preference == pitcher.preference;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teamId, memberId, appearances, balks, battersFaced, blownSaves, completeGames, earnedRunsAllowed, earnedRunAverage, flyOuts, gamesFinished, gamesStarted, groundOuts, holds, inheritedRunners, inningsPitched, losses, numberOfPitches, pickOffs, qualityStarts, reliefWins, saves, saveOpportunities, savePercentage, shutouts, strikeouts, unearnedRuns, walksAndHitsPerInningsPitched, wildPitches, wins, winningPercentage, preference);
    }

    @Override
    public String toString() {
        return "Pitcher{" +
                "id=" + id +
                ", teamId=" + teamId +
                ", memberId=" + memberId +
                ", appearances=" + appearances +
                ", balks=" + balks +
                ", battersFaced=" + battersFaced +
                ", blownSaves=" + blownSaves +
                ", completeGames=" + completeGames +
                ", earnedRunsAllowed=" + earnedRunsAllowed +
                ", earnedRunAverage=" + earnedRunAverage +
                ", flyOuts=" + flyOuts +
                ", gamesFinished=" + gamesFinished +
                ", gamesStarted=" + gamesStarted +
                ", groundOuts=" + groundOuts +
                ", holds=" + holds +
                ", inheritedRunners=" + inheritedRunners +
                ", inningsPitched=" + inningsPitched +
                ", losses=" + losses +
                ", numberOfPitches=" + numberOfPitches +
                ", pickOffs=" + pickOffs +
                ", qualityStarts=" + qualityStarts +
                ", reliefWins=" + reliefWins +
                ", saves=" + saves +
                ", saveOpportunities=" + saveOpportunities +
                ", savePercentage=" + savePercentage +
                ", shutouts=" + shutouts +
                ", strikeouts=" + strikeouts +
                ", unearnedRuns=" + unearnedRuns +
                ", walksAndHitsPerInningsPitched=" + walksAndHitsPerInningsPitched +
                ", wildPitches=" + wildPitches +
                ", wins=" + wins +
                ", winningPercentage=" + winningPercentage +
                ", preference=" + preference +
                '}';
    }
}
