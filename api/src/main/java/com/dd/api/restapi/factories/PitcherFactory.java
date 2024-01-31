package com.dd.api.restapi.factories;

import com.dd.api.restapi.models.Pitcher;
import com.dd.api.restapi.util.PitcherPreference;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PitcherFactory {
    public static Pitcher createPitcher(Pitcher pitcher, Connection connection) {
        
        String sql = (pitcher.getPreference().equals(PitcherPreference.LEFT)) ?
        """
            INSERT INTO sp24.dd_lhp (id, teamId, appearances, balks, battersFaced, blownSaves, completeGames, earnedRunsAllowed, earnedRunAverage,
            flyouts, gamesFinished, gamesStarted, groundouts, holds, inheritedRunners, inningsPitched, losses, numberOfPitches,
            pickoffs, qualityStarts, reliefWins, saves, saveOpportunities, savePercentage, shutouts, strikeouts, unearnedRuns,
            walksAndHitsPerInningPitched, wildPitches, wins, winningPercentage, ghosted_date)
            VALUES (?, ?, ?, ?,
                   ?, ?, ?, ?,
                   ?, ?, ?, ?,
                   ?, ?, ?, ?,
                   ?, ?, ?, ?,
                   ?, ?, ?, ?,
                   ?, ?, ?, ?,
                   ?, ?, ?, ?);
        """
        :
        """
           INSERT INTO sp24.dd_rhp (id, teamId, appearances, balks, battersFaced, blownSaves, completeGames, earnedRunsAllowed, earnedRunAverage,
           flyouts, gamesFinished, gamesStarted, groundouts, holds, inheritedRunners, inningsPitched, losses, numberOfPitches,
           pickoffs, qualityStarts, reliefWins, saves, saveOpportunities, savePercentage, shutouts, strikeouts, unearnedRuns,
           walksAndHitsPerInningPitched, wildPitches, wins, winningPercentage, ghosted_date)
           VALUES (?, ?, ?, ?,
                   ?, ?, ?, ?,
                   ?, ?, ?, ?,
                   ?, ?, ?, ?,
                   ?, ?, ?, ?,
                   ?, ?, ?, ?,
                   ?, ?, ?, ?,
                   ?, ?, ?, ?);
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
