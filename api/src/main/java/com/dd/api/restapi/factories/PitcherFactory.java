package com.dd.api.restapi.factories;

import com.dd.api.restapi.builders.PitcherBuilder;
import com.dd.api.restapi.models.Pitcher;
import com.dd.api.restapi.util.PitcherPreference;
import com.dd.api.util.TimeProvider;
import com.dd.api.util.TruncatedSystemTimeProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PitcherFactory {
    public static Pitcher createPitcher(Pitcher pitcher, Connection connection) {
        
        String sql = (pitcher.getPreference().equals(PitcherPreference.LEFT)) ?
        """
           INSERT INTO sp24.dd_lhp (id, teamId, appearances, balks, battersFaced, blownSaves, completeGames, earnedRunsAllowed, earnedRunAverage,
           flyouts, gamesFinished, gamesStarted, groundouts, holds, inheritedRunners, inningsPitched, losses, numberOfPitches,
           pickoffs, qualityStarts, reliefWins, saves, saveOpportunities, savePercentage, shutouts, strikeouts, unearnedRuns,
           walksAndHitsPerInningPitched, wildPitches, wins, winningPercentage, ghosted_date, throws, firstName, lastName)
           VALUES (?, ?, ?, ?,
                   ?, ?, ?, ?,
                   ?, ?, ?, ?,
                   ?, ?, ?, ?,
                   ?, ?, ?, ?,
                   ?, ?, ?, ?,
                   ?, ?, ?, ?,
                   ?, ?, ?, ?, ?, ?, ?);
        """
        :
        """
           INSERT INTO sp24.dd_rhp (id, teamId, appearances, balks, battersFaced, blownSaves, completeGames, earnedRunsAllowed, earnedRunAverage,
           flyouts, gamesFinished, gamesStarted, groundouts, holds, inheritedRunners, inningsPitched, losses, numberOfPitches,
           pickoffs, qualityStarts, reliefWins, saves, saveOpportunities, savePercentage, shutouts, strikeouts, unearnedRuns,
           walksAndHitsPerInningPitched, wildPitches, wins, winningPercentage, ghosted_date, throws, firstName, lastName)
           VALUES (?, ?, ?, ?,
                   ?, ?, ?, ?,
                   ?, ?, ?, ?,
                   ?, ?, ?, ?,
                   ?, ?, ?, ?,
                   ?, ?, ?, ?,
                   ?, ?, ?, ?,
                   ?, ?, ?, ?, ?, ?, ?);
        """;
        
        if(pitcher.getPreference().equals(PitcherPreference.SWITCH)) {
           sql = """
           INSERT INTO sp24.dd_rhp (id, teamId, appearances, balks, battersFaced, blownSaves, completeGames, earnedRunsAllowed, earnedRunAverage,
           flyouts, gamesFinished, gamesStarted, groundouts, holds, inheritedRunners, inningsPitched, losses, numberOfPitches,
           pickoffs, qualityStarts, reliefWins, saves, saveOpportunities, savePercentage, shutouts, strikeouts, unearnedRuns,
           walksAndHitsPerInningPitched, wildPitches, wins, winningPercentage, ghosted_date, throws, firstName, lastName)
           VALUES (?, ?, ?, ?,
                   ?, ?, ?, ?,
                   ?, ?, ?, ?,
                   ?, ?, ?, ?,
                   ?, ?, ?, ?,
                   ?, ?, ?, ?,
                   ?, ?, ?, ?,
                   ?, ?, ?, ?, ?, ?, ?);
            """;
            
            try(PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, pitcher.getId().toString());
                statement.setString(2, pitcher.getTeamId().toString());
                statement.setInt(3, pitcher.getAppearances());
                statement.setInt(4, pitcher.getBalks());
                statement.setInt(5, pitcher.getBattersFaced());
                statement.setInt(6, pitcher.getBlownSaves());
                statement.setInt(7, pitcher.getCompleteGames());
                statement.setInt(8, pitcher.getEarnedRunsAllowed());
                statement.setDouble(9, pitcher.getEarnedRunAverage());
                statement.setInt(10, pitcher.getFlyOuts());
                statement.setInt(11, pitcher.getGamesFinished());
                statement.setInt(12, pitcher.getGamesStarted());
                statement.setInt(13, pitcher.getGroundOuts());
                statement.setInt(14, pitcher.getHolds());
                statement.setInt(15, pitcher.getInheritedRunners());
                statement.setDouble(16, pitcher.getInningsPitched());
                statement.setInt(17, pitcher.getLosses());
                statement.setInt(18, pitcher.getNumberOfPitches());
                statement.setInt(19, pitcher.getPickOffs());
                statement.setInt(20, pitcher.getQualityStarts());
                statement.setInt(21, pitcher.getReliefWins());
                statement.setInt(22, pitcher.getSaves());
                statement.setInt(23, pitcher.getSaveOpportunities());
                statement.setDouble(24, pitcher.getSavePercentage());
                statement.setInt(25, pitcher.getShutouts());
                statement.setInt(26, pitcher.getStrikeouts());
                statement.setInt(27, pitcher.getUnearnedRuns());
                statement.setDouble(28, pitcher.getWalksAndHitsPerInningsPitched());
                statement.setInt(29, pitcher.getWildPitches());
                statement.setInt(30, pitcher.getWins());
                statement.setDouble(31, pitcher.getWinningPercentage());
                statement.setInt(32, 0);
                statement.setString(33, pitcher.getPreference().toString());
                statement.setString(34, pitcher.getFirstName());
                statement.setString(35, pitcher.getLastName());
                statement.executeUpdate();
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
                System.out.println(sql);
                return null;
            }
            
           sql = """
           INSERT INTO sp24.dd_lhp (id, teamId, appearances, balks, battersFaced, blownSaves, completeGames, earnedRunsAllowed, earnedRunAverage,
           flyouts, gamesFinished, gamesStarted, groundouts, holds, inheritedRunners, inningsPitched, losses, numberOfPitches,
           pickoffs, qualityStarts, reliefWins, saves, saveOpportunities, savePercentage, shutouts, strikeouts, unearnedRuns,
           walksAndHitsPerInningPitched, wildPitches, wins, winningPercentage, ghosted_date, throws, firstName, lastName)
           VALUES (?, ?, ?, ?,
                   ?, ?, ?, ?,
                   ?, ?, ?, ?,
                   ?, ?, ?, ?,
                   ?, ?, ?, ?,
                   ?, ?, ?, ?,
                   ?, ?, ?, ?,
                   ?, ?, ?, ?, ?, ?, ?);
            """;
            
            try(PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, pitcher.getId().toString());
                statement.setString(2, pitcher.getTeamId().toString());
                statement.setInt(3, pitcher.getAppearances());
                statement.setInt(4, pitcher.getBalks());
                statement.setInt(5, pitcher.getBattersFaced());
                statement.setInt(6, pitcher.getBlownSaves());
                statement.setInt(7, pitcher.getCompleteGames());
                statement.setInt(8, pitcher.getEarnedRunsAllowed());
                statement.setDouble(9, pitcher.getEarnedRunAverage());
                statement.setInt(10, pitcher.getFlyOuts());
                statement.setInt(11, pitcher.getGamesFinished());
                statement.setInt(12, pitcher.getGamesStarted());
                statement.setInt(13, pitcher.getGroundOuts());
                statement.setInt(14, pitcher.getHolds());
                statement.setInt(15, pitcher.getInheritedRunners());
                statement.setDouble(16, pitcher.getInningsPitched());
                statement.setInt(17, pitcher.getLosses());
                statement.setInt(18, pitcher.getNumberOfPitches());
                statement.setInt(19, pitcher.getPickOffs());
                statement.setInt(20, pitcher.getQualityStarts());
                statement.setInt(21, pitcher.getReliefWins());
                statement.setInt(22, pitcher.getSaves());
                statement.setInt(23, pitcher.getSaveOpportunities());
                statement.setDouble(24, pitcher.getSavePercentage());
                statement.setInt(25, pitcher.getShutouts());
                statement.setInt(26, pitcher.getStrikeouts());
                statement.setInt(27, pitcher.getUnearnedRuns());
                statement.setDouble(28, pitcher.getWalksAndHitsPerInningsPitched());
                statement.setInt(29, pitcher.getWildPitches());
                statement.setInt(30, pitcher.getWins());
                statement.setDouble(31, pitcher.getWinningPercentage());
                statement.setInt(32, 0);
                statement.setString(33, pitcher.getPreference().toString());
                statement.setString(34, pitcher.getFirstName());
                statement.setString(35, pitcher.getLastName());
                statement.executeUpdate();
                return pitcher;
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
                System.out.println(sql);
                return null;
            }
        }

        else {
            try(PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, pitcher.getId().toString());
                statement.setString(2, pitcher.getTeamId().toString());
                statement.setInt(3, pitcher.getAppearances());
                statement.setInt(4, pitcher.getBalks());
                statement.setInt(5, pitcher.getBattersFaced());
                statement.setInt(6, pitcher.getBlownSaves());
                statement.setInt(7, pitcher.getCompleteGames());
                statement.setInt(8, pitcher.getEarnedRunsAllowed());
                statement.setDouble(9, pitcher.getEarnedRunAverage());
                statement.setInt(10, pitcher.getFlyOuts());
                statement.setInt(11, pitcher.getGamesFinished());
                statement.setInt(12, pitcher.getGamesStarted());
                statement.setInt(13, pitcher.getGroundOuts());
                statement.setInt(14, pitcher.getHolds());
                statement.setInt(15, pitcher.getInheritedRunners());
                statement.setDouble(16, pitcher.getInningsPitched());
                statement.setInt(17, pitcher.getLosses());
                statement.setInt(18, pitcher.getNumberOfPitches());
                statement.setInt(19, pitcher.getPickOffs());
                statement.setInt(20, pitcher.getQualityStarts());
                statement.setInt(21, pitcher.getReliefWins());
                statement.setInt(22, pitcher.getSaves());
                statement.setInt(23, pitcher.getSaveOpportunities());
                statement.setDouble(24, pitcher.getSavePercentage());
                statement.setInt(25, pitcher.getShutouts());
                statement.setInt(26, pitcher.getStrikeouts());
                statement.setInt(27, pitcher.getUnearnedRuns());
                statement.setDouble(28, pitcher.getWalksAndHitsPerInningsPitched());
                statement.setInt(29, pitcher.getWildPitches());
                statement.setInt(30, pitcher.getWins());
                statement.setDouble(31, pitcher.getWinningPercentage());
                statement.setInt(32, 0);
                statement.setString(33, pitcher.getPreference().toString());
                statement.setString(34, pitcher.getFirstName());
                statement.setString(35, pitcher.getLastName());
                statement.executeUpdate();
                return pitcher;
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
                System.out.println(sql);
                return null;
            }
        }
    }
    
    public static List<Pitcher> getPitcherById(UUID id, Connection connection) {
        String sql = "SELECT * FROM sp24.dd_lhp WHERE id=? AND ghosted_date=0 UNION SELECT * FROM sp24.dd_rhp WHERE id=? AND ghosted_date=0";
        List<Pitcher> pitchers = new ArrayList<>();
        PitcherBuilder builder = new PitcherBuilder();

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id.toString());
            statement.setString(2, id.toString());
            ResultSet set = statement.executeQuery();

            while(set.next()) {
                pitchers.add(
                    builder.setId(UUID.fromString(set.getString("id")))
                        .setTeamId(UUID.fromString(set.getString("teamId")))
                        .setAppearances(set.getInt("appearances"))
                        .setBalks(set.getInt("balks"))
                        .setBattersFaced(set.getInt("battersFaced"))
                        .setBlownSaves(set.getInt("blownSaves"))
                        .setCompleteGames(set.getInt("completeGames"))
                        .setEarnedRunsAllowed(set.getInt("earnedRunsAllowed"))
                        .setEarnedRunAverage(set.getDouble("earnedRunAverage"))
                        .setFlyOuts(set.getInt("flyouts"))
                        .setGamesFinished(set.getInt("gamesFinished"))
                        .setGamesStarted(set.getInt("gamesStarted"))
                        .setGroundOuts(set.getInt("groundOuts"))
                        .setHolds(set.getInt("holds"))
                        .setInheritedRunners(set.getInt("inheritedRunners"))
                        .setInningsPitched(set.getDouble("inningsPitched"))
                        .setLosses(set.getInt("losses"))
                        .setNumberOfPitches(set.getInt("numberOfPitches"))
                        .setPickOffs(set.getInt("pickOffs"))
                        .setQualityStarts(set.getInt("qualityStarts"))
                        .setReliefWins(set.getInt("reliefWins"))
                        .setSaves(set.getInt("saves"))
                        .setSavePercentage(set.getDouble("savePercentage"))
                        .setSaveOpportunities(set.getInt("saveOpportunities"))
                        .setShutouts(set.getInt("shutouts"))
                        .setStrikeouts(set.getInt("strikeouts"))
                        .setUnearnedRuns(set.getInt("unearnedruns"))
                        .setWalksAndHitsPerInningsPitched(set.getDouble("walksAndHitsPerInningPitched"))
                        .setWildPitches(set.getInt("wildPitches"))
                        .setWins(set.getInt("wins"))
                        .setWinningPercentage(set.getDouble("winningPercentage"))
                        .setPreference(PitcherPreference.valueOf(set.getString("throws")))
                        .setFirstName(set.getString("firstName"))
                        .setLastName(set.getString("lastName"))
                        .createPitcher()
                );
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return pitchers;
    }
    
    public static List<Pitcher> getPitchersByTeam(UUID teamId, Connection connection) {
        String sql = "SELECT * FROM sp24.dd_lhp WHERE teamId=? AND ghosted_date=0 UNION SELECT * FROM sp24.dd_rhp WHERE teamId=? AND ghosted_date=0";
        List<Pitcher> pitchers = new ArrayList<>();
        PitcherBuilder builder = new PitcherBuilder();
        
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, teamId.toString());
            statement.setString(2, teamId.toString());
            ResultSet set = statement.executeQuery();
            while(set.next()) {
                pitchers.add(
                    builder.setId(UUID.fromString(set.getString("id")))
                        .setTeamId(UUID.fromString(set.getString("teamId")))
                        .setAppearances(set.getInt("appearances"))
                        .setBalks(set.getInt("balks"))
                        .setBattersFaced(set.getInt("battersFaced"))
                        .setBlownSaves(set.getInt("blownSaves"))
                        .setCompleteGames(set.getInt("completeGames"))
                        .setEarnedRunsAllowed(set.getInt("earnedRunsAllowed"))
                        .setEarnedRunAverage(set.getDouble("earnedRunAverage"))
                        .setFlyOuts(set.getInt("flyouts"))
                        .setGamesFinished(set.getInt("gamesFinished"))
                        .setGamesStarted(set.getInt("gamesStarted"))
                        .setGroundOuts(set.getInt("groundOuts"))
                        .setHolds(set.getInt("holds"))
                        .setInheritedRunners(set.getInt("inheritedRunners"))
                        .setInningsPitched(set.getDouble("inningsPitched"))
                        .setLosses(set.getInt("losses"))
                        .setNumberOfPitches(set.getInt("numberOfPitches"))
                        .setPickOffs(set.getInt("pickOffs"))
                        .setQualityStarts(set.getInt("qualityStarts"))
                        .setReliefWins(set.getInt("reliefWins"))
                        .setSaves(set.getInt("saves"))
                        .setSavePercentage(set.getDouble("savePercentage"))
                        .setSaveOpportunities(set.getInt("saveOpportunities"))
                        .setShutouts(set.getInt("shutouts"))
                        .setStrikeouts(set.getInt("strikeouts"))
                        .setUnearnedRuns(set.getInt("unearnedruns"))
                        .setWalksAndHitsPerInningsPitched(set.getDouble("walksAndHitsPerInningPitched"))
                        .setWildPitches(set.getInt("wildPitches"))
                        .setWins(set.getInt("wins"))
                        .setWinningPercentage(set.getDouble("winningPercentage"))
                        .setPreference(PitcherPreference.valueOf(set.getString("throws")))
                        .setFirstName(set.getString("firstName"))
                        .setLastName(set.getString("lastName"))
                        .createPitcher()
                );
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return pitchers;
    }
    
    public static Pitcher editPitcher(UUID id, Pitcher pitcher, Connection connection) {
       
        if (pitcher.getPreference().equals(PitcherPreference.SWITCH)) {
           String sql = """
              UPDATE sp24.dd_rhp
                SET
                    id = ?,
                    teamId = ?,
                    appearances = ?,
                    balks = ?,
                    battersFaced = ?,
                    blownSaves = ?,
                    completeGames = ?,
                    earnedRunsAllowed = ?,
                    earnedRunAverage = ?,
                    flyouts = ?,
                    gamesFinished = ?,
                    gamesStarted = ?,
                    groundouts = ?,
                    holds = ?,
                    inheritedRunners = ?,
                    inningsPitched = ?,
                    losses = ?,
                    numberOfPitches = ?,
                    pickoffs = ?,
                    qualityStarts = ?,
                    reliefWins = ?,
                    saves = ?,
                    saveOpportunities = ?,
                    savePercentage = ?,
                    shutouts = ?,
                    strikeouts = ?,
                    unearnedRuns = ?,
                    walksAndHitsPerInningPitched = ?,
                    wildPitches = ?,
                    wins = ?,
                    winningPercentage = ?,
                    ghosted_date = ?,
                    throws = ?,
                    firstName = ?,
                    lastName = ?
                    
                    WHERE id = ?
           """;
            
            try(PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, pitcher.getId().toString());
                statement.setString(2, pitcher.getTeamId().toString());
                statement.setInt(3, pitcher.getAppearances());
                statement.setInt(4, pitcher.getBalks());
                statement.setInt(5, pitcher.getBattersFaced());
                statement.setInt(6, pitcher.getBlownSaves());
                statement.setInt(7, pitcher.getCompleteGames());
                statement.setInt(8, pitcher.getEarnedRunsAllowed());
                statement.setDouble(9, pitcher.getEarnedRunAverage());
                statement.setInt(10, pitcher.getFlyOuts());
                statement.setInt(11, pitcher.getGamesFinished());
                statement.setInt(12, pitcher.getGamesStarted());
                statement.setInt(13, pitcher.getGroundOuts());
                statement.setInt(14, pitcher.getHolds());
                statement.setInt(15, pitcher.getInheritedRunners());
                statement.setDouble(16, pitcher.getInningsPitched());
                statement.setInt(17, pitcher.getLosses());
                statement.setInt(18, pitcher.getNumberOfPitches());
                statement.setInt(19, pitcher.getPickOffs());
                statement.setInt(20, pitcher.getQualityStarts());
                statement.setInt(21, pitcher.getReliefWins());
                statement.setInt(22, pitcher.getSaves());
                statement.setInt(23, pitcher.getSaveOpportunities());
                statement.setDouble(24, pitcher.getSavePercentage());
                statement.setInt(25, pitcher.getShutouts());
                statement.setInt(26, pitcher.getStrikeouts());
                statement.setInt(27, pitcher.getUnearnedRuns());
                statement.setDouble(28, pitcher.getWalksAndHitsPerInningsPitched());
                statement.setInt(29, pitcher.getWildPitches());
                statement.setInt(30, pitcher.getWins());
                statement.setDouble(31, pitcher.getWinningPercentage());
                statement.setInt(32, 0);
                statement.setString(33, pitcher.getPreference().toString());
                statement.setString(34, pitcher.getFirstName());
                statement.setString(35, pitcher.getLastName());
                statement.setString(36, id.toString());
                statement.executeUpdate();
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
                return null;
            }
            
            sql =  """
            UPDATE sp24.dd_rhp
                SET
            id = ?,
            teamId = ?,
            appearances = ?,
            balks = ?,
            battersFaced = ?,
            blownSaves = ?,
            completeGames = ?,
            earnedRunsAllowed = ?,
            earnedRunAverage = ?,
            flyouts = ?,
            gamesFinished = ?,
            gamesStarted = ?,
            groundouts = ?,
            holds = ?,
            inheritedRunners = ?,
            inningsPitched = ?,
            losses = ?,
            numberOfPitches = ?,
            pickoffs = ?,
            qualityStarts = ?,
            reliefWins = ?,
            saves = ?,
            saveOpportunities = ?,
            savePercentage = ?,
            shutouts = ?,
            strikeouts = ?,
            unearnedRuns = ?,
            walksAndHitsPerInningPitched = ?,
            wildPitches = ?,
            wins = ?,
            winningPercentage = ?,
            ghosted_date = ?,
                    throws = ?,
            firstName = ?,
            lastName = ?
            
            WHERE id = ?
            """;
	     
	     try(PreparedStatement statement = connection.prepareStatement(sql)) {
		 statement.setString(1, pitcher.getId().toString());
		 statement.setString(2, pitcher.getTeamId().toString());
		 statement.setInt(3, pitcher.getAppearances());
		 statement.setInt(4, pitcher.getBalks());
		 statement.setInt(5, pitcher.getBattersFaced());
		 statement.setInt(6, pitcher.getBlownSaves());
		 statement.setInt(7, pitcher.getCompleteGames());
		 statement.setInt(8, pitcher.getEarnedRunsAllowed());
		 statement.setDouble(9, pitcher.getEarnedRunAverage());
		 statement.setInt(10, pitcher.getFlyOuts());
		 statement.setInt(11, pitcher.getGamesFinished());
		 statement.setInt(12, pitcher.getGamesStarted());
		 statement.setInt(13, pitcher.getGroundOuts());
		 statement.setInt(14, pitcher.getHolds());
		 statement.setInt(15, pitcher.getInheritedRunners());
		 statement.setDouble(16, pitcher.getInningsPitched());
		 statement.setInt(17, pitcher.getLosses());
		 statement.setInt(18, pitcher.getNumberOfPitches());
		 statement.setInt(19, pitcher.getPickOffs());
		 statement.setInt(20, pitcher.getQualityStarts());
		 statement.setInt(21, pitcher.getReliefWins());
		 statement.setInt(22, pitcher.getSaves());
		 statement.setInt(23, pitcher.getSaveOpportunities());
		 statement.setDouble(24, pitcher.getSavePercentage());
		 statement.setInt(25, pitcher.getShutouts());
		 statement.setInt(26, pitcher.getStrikeouts());
		 statement.setInt(27, pitcher.getUnearnedRuns());
		 statement.setDouble(28, pitcher.getWalksAndHitsPerInningsPitched());
		 statement.setInt(29, pitcher.getWildPitches());
		 statement.setInt(30, pitcher.getWins());
		 statement.setDouble(31, pitcher.getWinningPercentage());
		 statement.setInt(32, 0);
		 statement.setString(33, pitcher.getPreference().toString());
		 statement.setString(34, pitcher.getFirstName());
		 statement.setString(35, pitcher.getLastName());
		 statement.setString(36, id.toString());
		 statement.executeUpdate();
		 return pitcher;
	     }
	     catch (Exception ex) {
		 System.out.println(ex.getMessage());
		 return null;
	     }
	 }
	 else {
	     String sql = pitcher.getPreference().equals(PitcherPreference.RIGHT) ? """
            UPDATE sp24.dd_rhp
                SET
                    id = ?,
                    teamId = ?,
                    appearances = ?,
                    balks = ?,
                    battersFaced = ?,
                    blownSaves = ?,
                    completeGames = ?,
                    earnedRunsAllowed = ?,
                    earnedRunAverage = ?,
                    flyouts = ?,
                    gamesFinished = ?,
                    gamesStarted = ?,
                    groundouts = ?,
                    holds = ?,
                    inheritedRunners = ?,
                    inningsPitched = ?,
                    losses = ?,
                    numberOfPitches = ?,
                    pickoffs = ?,
                    qualityStarts = ?,
                    reliefWins = ?,
                    saves = ?,
                    saveOpportunities = ?,
                    savePercentage = ?,
                    shutouts = ?,
                    strikeouts = ?,
                    unearnedRuns = ?,
                    walksAndHitsPerInningPitched = ?,
                    wildPitches = ?,
                    wins = ?,
                    winningPercentage = ?,
                    ghosted_date = ?,
                    throws = ?,
                    firstName = ?,
                    lastName = ?
                    
                    WHERE id = ?
                """
                :
                """
		UPDATE sp24.dd_lhp
		SET
		    id = ?,
		    teamId = ?,
		    appearances = ?,
		    balks = ?,
		    battersFaced = ?,
		    blownSaves = ?,
		    completeGames = ?,
		    earnedRunsAllowed = ?,
		    earnedRunAverage = ?,
		    flyouts = ?,
		    gamesFinished = ?,
		    gamesStarted = ?,
		    groundouts = ?,
		    holds = ?,
		    inheritedRunners = ?,
		    inningsPitched = ?,
		    losses = ?,
		    numberOfPitches = ?,
		    pickoffs = ?,
		    qualityStarts = ?,
		    reliefWins = ?,
		    saves = ?,
		    saveOpportunities = ?,
		    savePercentage = ?,
		    shutouts = ?,
		    strikeouts = ?,
		    unearnedRuns = ?,
		    walksAndHitsPerInningPitched = ?,
		    wildPitches = ?,
		    wins = ?,
		    winningPercentage = ?,
		    ghosted_date = ?,
		    throws = ?,
		    firstName = ?,
		    lastName = ?
		    WHERE id = ?
		""";
            try(PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, pitcher.getId().toString());
                statement.setString(2, pitcher.getTeamId().toString());
                statement.setInt(3, pitcher.getAppearances());
                statement.setInt(4, pitcher.getBalks());
                statement.setInt(5, pitcher.getBattersFaced());
                statement.setInt(6, pitcher.getBlownSaves());
                statement.setInt(7, pitcher.getCompleteGames());
                statement.setInt(8, pitcher.getEarnedRunsAllowed());
                statement.setDouble(9, pitcher.getEarnedRunAverage());
                statement.setInt(10, pitcher.getFlyOuts());
                statement.setInt(11, pitcher.getGamesFinished());
                statement.setInt(12, pitcher.getGamesStarted());
                statement.setInt(13, pitcher.getGroundOuts());
                statement.setInt(14, pitcher.getHolds());
                statement.setInt(15, pitcher.getInheritedRunners());
                statement.setDouble(16, pitcher.getInningsPitched());
                statement.setInt(17, pitcher.getLosses());
                statement.setInt(18, pitcher.getNumberOfPitches());
                statement.setInt(19, pitcher.getPickOffs());
                statement.setInt(20, pitcher.getQualityStarts());
                statement.setInt(21, pitcher.getReliefWins());
                statement.setInt(22, pitcher.getSaves());
                statement.setInt(23, pitcher.getSaveOpportunities());
                statement.setDouble(24, pitcher.getSavePercentage());
                statement.setInt(25, pitcher.getShutouts());
                statement.setInt(26, pitcher.getStrikeouts());
                statement.setInt(27, pitcher.getUnearnedRuns());
                statement.setDouble(28, pitcher.getWalksAndHitsPerInningsPitched());
                statement.setInt(29, pitcher.getWildPitches());
                statement.setInt(30, pitcher.getWins());
                statement.setDouble(31, pitcher.getWinningPercentage());
                statement.setInt(32, 0);
                statement.setString(33, pitcher.getPreference().toString());
                statement.setString(34, pitcher.getFirstName());
                statement.setString(35, pitcher.getLastName());
                statement.setString(36, id.toString());
                statement.executeUpdate();
                return pitcher;
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
                return null;
            }
        }
    }
    
    public static boolean deletePitcher(UUID id, Connection connection) {
    
        String sql = "UPDATE sp24.dd_lhp SET ghosted_date=? WHERE id=?; UPDATE sp24.dd_rhp SET ghosted_date=? WHERE id=?";
        TruncatedSystemTimeProvider provider = new TruncatedSystemTimeProvider();
        
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, (int) provider.provideTime());
            statement.setString(2, id.toString());
            statement.setInt(3, (int) provider.provideTime());
            statement.setString(4, id.toString());
            statement.executeUpdate();
            return true;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public static boolean deleteTeamPitchers(UUID teamId, Connection connection) {
        String sql = "UPDATE sp24.dd_lhp SET ghosted_date=? WHERE teamId=?; UPDATE sp24.dd_rhp SET ghosted_date=? WHERE teamId=?";
        TruncatedSystemTimeProvider provider = new TruncatedSystemTimeProvider();
        
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, (int) provider.provideTime());
            statement.setString(2, teamId.toString());
            statement.setInt(3, (int) provider.provideTime());
            statement.setString(4, teamId.toString());
            statement.executeUpdate();
            return true;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}