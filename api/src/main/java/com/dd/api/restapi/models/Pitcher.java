package com.dd.api.restapi.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name="dd_pitchers", schema = "sp24")
public class Pitcher {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private long ghostedDate;
    
    // L, R, S
    private Character preference;
    private int appearances;
    private int balks;
    private int battersFaces;
    private int blownSaves;
    private int completeGames;
    private int earnedRuns;
    private double earnedRunAverage;
    private int flyout;
    private int gamesFinished;
    private int gamesStarted;
    private int groundouts;
    private int holds;
    private int inheritedRunners;
    private double inningsPitched;
    private int losses;
    private int numberOfPitches;
    private int pickoffs;
    private int qualityStarts;
    private int reliefWins;
    private int saves;
    private int saveOpportunities;
    private double savePercentage;
    private int shutouts;
    private int strikeouts;
    private int unearnedRun;
    private double walksAndHitsPerInningPitched;
    private int wildPitches;
    private int wins;
    private double winningPercentage;
    
    @JsonCreator
    public Pitcher(String firstName,
		   String lastName,
		   Character preference,
		   int appearances,
		   int balks,
		   int battersFaces,
		   int blownSaves,
		   int completeGames,
		   int earnedRuns,
		   double earnedRunAverage,
		   int flyout,
		   int gamesFinished,
		   int gamesStarted,
		   int groundouts,
		   int holds,
		   int inheritedRunners,
		   double inningsPitched,
		   int losses,
		   int numberOfPitches,
		   int pickoffs,
		   int qualityStarts,
		   int reliefWins,
		   int saves,
		   int saveOpportunities,
		   double savePercentage,
		   int shutouts,
		   int strikeouts,
		   int unearnedRun,
		   double walksAndHitsPerInningPitched,
		   int wildPitches,
		   int wins,
		   double winningPercentage) {
	this.firstName = firstName;
	this.lastName = lastName;
	this.preference = preference;
	this.appearances = appearances;
	this.balks = balks;
	this.battersFaces = battersFaces;
	this.blownSaves = blownSaves;
	this.completeGames = completeGames;
	this.earnedRuns = earnedRuns;
	this.earnedRunAverage = earnedRunAverage;
	this.flyout = flyout;
	this.gamesFinished = gamesFinished;
	this.gamesStarted = gamesStarted;
	this.groundouts = groundouts;
	this.holds = holds;
	this.inheritedRunners = inheritedRunners;
	this.inningsPitched = inningsPitched;
	this.losses = losses;
	this.numberOfPitches = numberOfPitches;
	this.pickoffs = pickoffs;
	this.qualityStarts = qualityStarts;
	this.reliefWins = reliefWins;
	this.saves = saves;
	this.saveOpportunities = saveOpportunities;
	this.savePercentage = savePercentage;
	this.shutouts = shutouts;
	this.strikeouts = strikeouts;
	this.unearnedRun = unearnedRun;
	this.walksAndHitsPerInningPitched = walksAndHitsPerInningPitched;
	this.wildPitches = wildPitches;
	this.wins = wins;
	this.winningPercentage = winningPercentage;
    }
    
    public Pitcher() {
    }
    
    public Long getId() {
	return id;
    }
    
    public void setId(Long id) {
	this.id = id;
    }
    
    public String getFirstName() {
	return firstName;
    }
    
    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }
    
    public String getLastName() {
	return lastName;
    }
    
    public void setLastName(String lastName) {
	this.lastName = lastName;
    }
    
    public Character getPreference() {
	return preference;
    }
    
    public void setPreference(Character preference) {
	this.preference = preference;
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
    
    public int getBattersFaces() {
	return battersFaces;
    }
    
    public void setBattersFaces(int battersFaces) {
	this.battersFaces = battersFaces;
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
    
    public int getEarnedRuns() {
	return earnedRuns;
    }
    
    public void setEarnedRuns(int earnedRuns) {
	this.earnedRuns = earnedRuns;
    }
    
    public double getEarnedRunAverage() {
	return earnedRunAverage;
    }
    
    public void setEarnedRunAverage(double earnedRunAverage) {
	this.earnedRunAverage = earnedRunAverage;
    }
    
    public int getFlyout() {
	return flyout;
    }
    
    public void setFlyout(int flyout) {
	this.flyout = flyout;
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
    
    public int getGroundouts() {
	return groundouts;
    }
    
    public void setGroundouts(int groundouts) {
	this.groundouts = groundouts;
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
    
    public int getPickoffs() {
	return pickoffs;
    }
    
    public void setPickoffs(int pickoffs) {
	this.pickoffs = pickoffs;
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
    
    public int getUnearnedRun() {
	return unearnedRun;
    }
    
    public void setUnearnedRun(int unearnedRun) {
	this.unearnedRun = unearnedRun;
    }
    
    public double getWalksAndHitsPerInningPitched() {
	return walksAndHitsPerInningPitched;
    }
    
    public void setWalksAndHitsPerInningPitched(double walksAndHitsPerInningPitched) {
	this.walksAndHitsPerInningPitched = walksAndHitsPerInningPitched;
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
    
    public long getGhostedDate() {
	return ghostedDate;
    }
    
    public void setGhostedDate(long ghostedDate) {
	this.ghostedDate = ghostedDate;
    }
    
    @Override
    public boolean equals(Object o) {
	if (this == o) {
	    return true;
	}
	if (o == null || getClass() != o.getClass()) {
	    return false;
	}
	Pitcher pitcher = (Pitcher) o;
	return appearances == pitcher.appearances && balks == pitcher.balks && battersFaces == pitcher.battersFaces && blownSaves == pitcher.blownSaves && completeGames == pitcher.completeGames && earnedRuns == pitcher.earnedRuns && Double.compare(earnedRunAverage, pitcher.earnedRunAverage) == 0 && flyout == pitcher.flyout && gamesFinished == pitcher.gamesFinished && gamesStarted == pitcher.gamesStarted && groundouts == pitcher.groundouts && holds == pitcher.holds && inheritedRunners == pitcher.inheritedRunners && Double.compare(inningsPitched, pitcher.inningsPitched) == 0 && losses == pitcher.losses && numberOfPitches == pitcher.numberOfPitches && pickoffs == pitcher.pickoffs && qualityStarts == pitcher.qualityStarts && reliefWins == pitcher.reliefWins && saves == pitcher.saves && saveOpportunities == pitcher.saveOpportunities && Double.compare(savePercentage, pitcher.savePercentage) == 0 && shutouts == pitcher.shutouts && strikeouts == pitcher.strikeouts && unearnedRun == pitcher.unearnedRun && Double.compare(walksAndHitsPerInningPitched, pitcher.walksAndHitsPerInningPitched) == 0 && wildPitches == pitcher.wildPitches && wins == pitcher.wins && Double.compare(winningPercentage, pitcher.winningPercentage) == 0 && Objects.equals(id, pitcher.id) && Objects.equals(firstName, pitcher.firstName) && Objects.equals(lastName, pitcher.lastName) && Objects.equals(preference, pitcher.preference);
    }
    
    @Override
    public int hashCode() {
	return Objects.hash(id, firstName, lastName, preference, appearances, balks, battersFaces, blownSaves, completeGames, earnedRuns, earnedRunAverage, flyout, gamesFinished, gamesStarted, groundouts, holds, inheritedRunners, inningsPitched, losses, numberOfPitches, pickoffs, qualityStarts, reliefWins, saves, saveOpportunities, savePercentage, shutouts, strikeouts, unearnedRun, walksAndHitsPerInningPitched, wildPitches, wins, winningPercentage);
    }
}