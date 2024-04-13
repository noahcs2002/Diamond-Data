package com.dd.api.restapi.models;

import com.dd.api.restapi.services.StatisticsService;
import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "dd_pitchers", schema = "sp24")
public class Pitcher {
    // Stat count: 30

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;

    private String firstName;
    private String lastName;
    private long ghostedDate;

    // L, R, S
    private String preference;
    private int appearances;
    private int balks;
    private int battersFaced;
    private int blownSaves;
    private int completeGames;
    private int earnedRuns;
    private double earnedRunAverage;
    private int flyouts;
    private int gamesFinished;
    private int gamesStarted;
    private int groundouts;
    private int holds;
    private int hits;
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
    private int unearnedRuns;
    private double walksAndHitsPerInningPitched;
    private int walks;
    private int wildPitches;
    private int wins;
    private double winningPercentage;

    @JsonCreator
    public Pitcher(String firstName,
                   String lastName,
                   String preference,
                   Team team,
                   int appearances,
                   int balks,
                   int battersFaced,
                   int blownSaves,
                   int completeGames,
                   int earnedRuns,
                   double earnedRunAverage,
                   int flyouts,
                   int gamesFinished,
                   int gamesStarted,
                   int groundouts,
                   int holds,
                   int hits,
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
                   int unearnedRuns,
                   int walks,
                   double walksAndHitsPerInningPitched,
                   int wildPitches,
                   int wins,
                   double winningPercentage) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.preference = preference;
        this.appearances = appearances;
        this.balks = balks;
        this.battersFaced = battersFaced;
        this.blownSaves = blownSaves;
        this.completeGames = completeGames;
        this.earnedRuns = earnedRuns;
        this.earnedRunAverage = earnedRunAverage;
        this.flyouts = flyouts;
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
        this.unearnedRuns = unearnedRuns;
        this.walksAndHitsPerInningPitched = walksAndHitsPerInningPitched;
        this.wildPitches = wildPitches;
        this.wins = wins;
        this.winningPercentage = winningPercentage;
        this.team = team;
        this.walks = walks;
        this.hits = hits;
    }

    public Pitcher() {
        // Empty for hibernate
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

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
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
        return flyouts;
    }

    public void setFlyout(int flyouts) {
        this.flyouts = flyouts;
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
        return unearnedRuns;
    }

    public void setUnearnedRun(int unearnedRuns) {
        this.unearnedRuns = unearnedRuns;
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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getFlyouts() {
        return flyouts;
    }

    public void setFlyouts(int flyouts) {
        this.flyouts = flyouts;
    }

    public int getUnearnedRuns() {
        return unearnedRuns;
    }

    public void setUnearnedRuns(int unearnedRuns) {
        this.unearnedRuns = unearnedRuns;
    }

    public double getWalks() {
        return walks;
    }

    public void setWalks(int walks) {
        this.walks = walks;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pitcher pitcher = (Pitcher) o;
        return ghostedDate == pitcher.ghostedDate && appearances == pitcher.appearances && balks == pitcher.balks && battersFaced == pitcher.battersFaced && blownSaves == pitcher.blownSaves && completeGames == pitcher.completeGames && earnedRuns == pitcher.earnedRuns && Double.compare(earnedRunAverage, pitcher.earnedRunAverage) == 0 && flyouts == pitcher.flyouts && gamesFinished == pitcher.gamesFinished && gamesStarted == pitcher.gamesStarted && groundouts == pitcher.groundouts && holds == pitcher.holds && hits == pitcher.hits && inheritedRunners == pitcher.inheritedRunners && Double.compare(inningsPitched, pitcher.inningsPitched) == 0 && losses == pitcher.losses && numberOfPitches == pitcher.numberOfPitches && pickoffs == pitcher.pickoffs && qualityStarts == pitcher.qualityStarts && reliefWins == pitcher.reliefWins && saves == pitcher.saves && saveOpportunities == pitcher.saveOpportunities && Double.compare(savePercentage, pitcher.savePercentage) == 0 && shutouts == pitcher.shutouts && strikeouts == pitcher.strikeouts && unearnedRuns == pitcher.unearnedRuns && Double.compare(walksAndHitsPerInningPitched, pitcher.walksAndHitsPerInningPitched) == 0 && Double.compare(walks, pitcher.walks) == 0 && wildPitches == pitcher.wildPitches && wins == pitcher.wins && Double.compare(winningPercentage, pitcher.winningPercentage) == 0 && Objects.equals(id, pitcher.id) && Objects.equals(team, pitcher.team) && Objects.equals(firstName, pitcher.firstName) && Objects.equals(lastName, pitcher.lastName) && Objects.equals(preference, pitcher.preference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, team, firstName, lastName, ghostedDate, preference, appearances, balks, battersFaced, blownSaves, completeGames, earnedRuns, earnedRunAverage, flyouts, gamesFinished, gamesStarted, groundouts, holds, hits, inheritedRunners, inningsPitched, losses, numberOfPitches, pickoffs, qualityStarts, reliefWins, saves, saveOpportunities, savePercentage, shutouts, strikeouts, unearnedRuns, walksAndHitsPerInningPitched, walks, wildPitches, wins, winningPercentage);
    }

    public Pitcher applyStatisticsUpdate(StatisticsService statisticsService) {
        statisticsService.updatePitcherStatistics(this);
        return this;
    }

    @Override
    public String toString() {
        return "Pitcher{" +
                "id=" + id +
                ", team=" + team +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", ghostedDate=" + ghostedDate +
                ", preference=" + preference +
                ", appearances=" + appearances +
                ", balks=" + balks +
                ", battersFaced=" + battersFaced +
                ", blownSaves=" + blownSaves +
                ", completeGames=" + completeGames +
                ", earnedRuns=" + earnedRuns +
                ", earnedRunAverage=" + earnedRunAverage +
                ", flyouts=" + flyouts +
                ", gamesFinished=" + gamesFinished +
                ", gamesStarted=" + gamesStarted +
                ", groundouts=" + groundouts +
                ", holds=" + holds +
                ", hits=" + hits +
                ", inheritedRunners=" + inheritedRunners +
                ", inningsPitched=" + inningsPitched +
                ", losses=" + losses +
                ", numberOfPitches=" + numberOfPitches +
                ", pickoffs=" + pickoffs +
                ", qualityStarts=" + qualityStarts +
                ", reliefWins=" + reliefWins +
                ", saves=" + saves +
                ", saveOpportunities=" + saveOpportunities +
                ", savePercentage=" + savePercentage +
                ", shutouts=" + shutouts +
                ", strikeouts=" + strikeouts +
                ", unearnedRuns=" + unearnedRuns +
                ", walksAndHitsPerInningPitched=" + walksAndHitsPerInningPitched +
                ", walks=" + walks +
                ", wildPitches=" + wildPitches +
                ", wins=" + wins +
                ", winningPercentage=" + winningPercentage +
                '}';
    }
}