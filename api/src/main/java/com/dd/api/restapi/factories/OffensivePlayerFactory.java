package com.dd.api.restapi.factories;

import com.dd.api.restapi.builders.OffensivePlayerBuilder;
import com.dd.api.restapi.models.OffensivePlayer;
import com.dd.api.restapi.util.BatterPreference;
import com.dd.api.util.TruncatedSystemTimeProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OffensivePlayerFactory {
    
    public static OffensivePlayer createPlayer(OffensivePlayer player, Connection connection) {
	
	if (player.getPreference().equals(BatterPreference.SWITCH)) {
	    String sql = """
		INSERT INTO sp24.dd_offense_left (
		    id,
		    teamId,
		    atBats,
		    average,
		    caughtStealingPercentage,
		    doubles,
		    extraBaseHits,
		    gamesPlayed,
		    grandSlams,
		    groundIntoDoublePlay,
		    groundOutVsFlyOut,
		    hitByPitch,
		    hits,
		    homeruns,
		    intentionalWalks,
		    leftOnBase,
		    onBasePercentage,
		    onBasePlusSlugging,
		    plateAppearances,
		    reachedOnError,
		    runs,
		    runsBattedIn,
		    sacrificeBunts,
		    sacrificeFlies,
		    singles,
		    slugging,
		    stolenBases,
		    totalBases,
		    triples,
		    walks,
		    walkOffs,
		    ghosted_date,
		    firstName,
		    lastName, stolenBaseAttempts ) VALUES (
		    ?, ?, ?, ?, ?,
		    ?, ?, ?, ?, ?,
		    ?, ?, ?, ?, ?,
		    ?, ?, ?, ?, ?,
		    ?, ?, ?, ?, ?,
		    ?, ?, ?, ?, ?,
		    ?, ?, ?, ?, ? )
	    """;
	    
	    try(PreparedStatement statement = connection.prepareStatement(sql)) {
		statement.setString(1, player.getId().toString());
		statement.setString(2, player.getTeamId().toString());
		statement.setInt(3, player.getAtBats());
		statement.setDouble(4, player.getAverage());
		statement.setDouble(5, player.getCaughtStealingPercentage());
		statement.setInt(6, player.getDoubles());
		statement.setInt(7, player.getExtraBaseHits());
		statement.setInt(8, player.getGamesPlayed());
		statement.setInt(9, player.getGrandSlams());
		statement.setInt(10, player.getGroundIntoDoublePlay());
		statement.setDouble(11, player.getGroundOutVsFlyOut());
		statement.setInt(12, player.getHitByPitch());
		statement.setInt(13, player.getHits());
		statement.setInt(14, player.getHomeRuns());
		statement.setInt(15, player.getIntentionalWalks());
		statement.setInt(16, player.getLeftOnBase());
		statement.setDouble(17, player.getOnBasePercentage());
		statement.setDouble(18, player.getOnBasePlusSlugging());
		statement.setInt(19, player.getPlateAppearances());
		statement.setInt(20, player.getReachedOnError());
		statement.setInt(21, player.getRuns());
		statement.setInt(22, player.getRunsBattedIn());
		statement.setInt(23, player.getSacrificeBunts());
		statement.setInt(24, player.getSacrificeFlies());
		statement.setInt(25, player.getSingles());
		statement.setDouble(26, player.getSlugging());
		statement.setInt(27, player.getStolenBases());
		statement.setInt(28, player.getTotalBases());
		statement.setInt(29, player.getTriples());
		statement.setInt(30, player.getWalkOffs());
		statement.setInt(31, player.getWalkOffs());
		statement.setInt(32, 0);
		statement.setString(33, player.getFirstName());
		statement.setString(34, player.getLastName());
		statement.setInt(45, player.getStolenBaseAttempts());
		statement.executeUpdate();
	    }
	    catch (Exception ex) {
		System.out.println(ex.getMessage());
		return null;
	    }
	    
	    sql = """
		    INSERT INTO sp24.dd_offense_right (
		    id,
		    teamId,
		    atBats,
		    average,
		    caughtStealingPercentage,
		    doubles,
		    extraBaseHits,
		    gamesPlayed,
		    grandSlams,
		    groundIntoDoublePlay,
		    groundOutVsFlyOut,
		    hitByPitch,
		    hits,
		    homeruns,
		    intentionalWalks,
		    leftOnBase,
		    onBasePercentage,
		    onBasePlusSlugging,
		    plateAppearances,
		    reachedOnError,
		    runs,
		    runsBattedIn,
		    sacrificeBunts,
		    sacrificeFlies,
		    singles,
		    slugging,
		    stolenBases,
		    totalBases,
		    triples,
		    walks,
		    walkOffs,
		    ghosted_date,
		    firstName,
		    lastName, stolenBaseAttempts ) VALUES (
		    ?, ?, ?, ?, ?,
		    ?, ?, ?, ?, ?,
		    ?, ?, ?, ?, ?,
		    ?, ?, ?, ?, ?,
		    ?, ?, ?, ?, ?,
		    ?, ?, ?, ?, ?,
		    ?, ?, ?, ?, ? )
	"""    ;
	    
	    try(PreparedStatement statement = connection.prepareStatement(sql)) {
		statement.setString(1, player.getId().toString());
		statement.setString(2, player.getTeamId().toString());
		statement.setInt(3, player.getAtBats());
		statement.setDouble(4, player.getAverage());
		statement.setDouble(5, player.getCaughtStealingPercentage());
		statement.setInt(6, player.getDoubles());
		statement.setInt(7, player.getExtraBaseHits());
		statement.setInt(8, player.getGamesPlayed());
		statement.setInt(9, player.getGrandSlams());
		statement.setInt(10, player.getGroundIntoDoublePlay());
		statement.setDouble(11, player.getGroundOutVsFlyOut());
		statement.setInt(12, player.getHitByPitch());
		statement.setInt(13, player.getHits());
		statement.setInt(14, player.getHomeRuns());
		statement.setInt(15, player.getIntentionalWalks());
		statement.setInt(16, player.getLeftOnBase());
		statement.setDouble(17, player.getOnBasePercentage());
		statement.setDouble(18, player.getOnBasePlusSlugging());
		statement.setInt(19, player.getPlateAppearances());
		statement.setInt(20, player.getReachedOnError());
		statement.setInt(21, player.getRuns());
		statement.setInt(22, player.getRunsBattedIn());
		statement.setInt(23, player.getSacrificeBunts());
		statement.setInt(24, player.getSacrificeFlies());
		statement.setInt(25, player.getSingles());
		statement.setDouble(26, player.getSlugging());
		statement.setInt(27, player.getStolenBases());
		statement.setInt(28, player.getTotalBases());
		statement.setInt(29, player.getTriples());
		statement.setInt(30, player.getWalkOffs());
		statement.setInt(31, player.getWalkOffs());
		statement.setInt(32, 0);
		statement.setString(33, player.getFirstName());
		statement.setString(34, player.getLastName());
		statement.setInt(35, player.getStolenBaseAttempts());
		statement.executeUpdate();
		return player;
	    }
	    catch (Exception ex) {
		System.out.println(ex.getMessage());
		return null;
	    }
	}
	
	else {
	    String sql = player.getPreference().equals(BatterPreference.LEFT) ?
		"""
		INSERT INTO sp24.dd_offense_left (
		    id,
		    teamId,
		    atBats,
		    average,
		    caughtStealingPercentage,
		    doubles,
		    extraBaseHits,
		    gamesPlayed,
		    grandSlams,
		    groundIntoDoublePlay,
		    groundOutVsFlyOut,
		    hitByPitch,
		    hits,
		    homeruns,
		    intentionalWalks,
		    leftOnBase,
		    onBasePercentage,
		    onBasePlusSlugging,
		    plateAppearances,
		    reachedOnError,
		    runs,
		    runsBattedIn,
		    sacrificeBunts,
		    sacrificeFlies,
		    singles,
		    slugging,
		    stolenBases,
		    totalBases,
		    triples,
		    walks,
		    walkOffs,
		    ghosted_date,
		    firstName,
		    lastName, stolenBaseAttempts ) VALUES (
		    ?, ?, ?, ?, ?,
		    ?, ?, ?, ?, ?,
		    ?, ?, ?, ?, ?,
		    ?, ?, ?, ?, ?,
		    ?, ?, ?, ?, ?,
		    ?, ?, ?, ?, ?,
		    ?, ?, ?, ?, ? )
	        """
		:
		"""
		   INSERT INTO sp24.dd_offense_right (
		    id,
		    teamId,
		    atBats,
		    average,
		    caughtStealingPercentage,
		    doubles,
		    extraBaseHits,
		    gamesPlayed,
		    grandSlams,
		    groundIntoDoublePlay,
		    groundOutVsFlyOut,
		    hitByPitch,
		    hits,
		    homeruns,
		    intentionalWalks,
		    leftOnBase,
		    onBasePercentage,
		    onBasePlusSlugging,
		    plateAppearances,
		    reachedOnError,
		    runs,
		    runsBattedIn,
		    sacrificeBunts,
		    sacrificeFlies,
		    singles,
		    slugging,
		    stolenBases,
		    totalBases,
		    triples,
		    walks,
		    walkOffs,
		    ghosted_date,
		    firstName,
		    lastName, stolenBaseAttempts ) VALUES (
		    ?, ?, ?, ?, ?,
		    ?, ?, ?, ?, ?,
		    ?, ?, ?, ?, ?,
		    ?, ?, ?, ?, ?,
		    ?, ?, ?, ?, ?,
		    ?, ?, ?, ?, ?,
		    ?, ?, ?, ?, ? )
	"""    ;
 
	    try(PreparedStatement statement = connection.prepareStatement(sql)) {
		statement.setString(1, player.getId().toString());
		statement.setString(2, player.getTeamId().toString());
		statement.setInt(3, player.getAtBats());
		statement.setDouble(4, player.getAverage());
		statement.setDouble(5, player.getCaughtStealingPercentage());
		statement.setInt(6, player.getDoubles());
		statement.setInt(7, player.getExtraBaseHits());
		statement.setInt(8, player.getGamesPlayed());
		statement.setInt(9, player.getGrandSlams());
		statement.setInt(10, player.getGroundIntoDoublePlay());
		statement.setDouble(11, player.getGroundOutVsFlyOut());
		statement.setInt(12, player.getHitByPitch());
		statement.setInt(13, player.getHits());
		statement.setInt(14, player.getHomeRuns());
		statement.setInt(15, player.getIntentionalWalks());
		statement.setInt(16, player.getLeftOnBase());
		statement.setDouble(17, player.getOnBasePercentage());
		statement.setDouble(18, player.getOnBasePlusSlugging());
		statement.setInt(19, player.getPlateAppearances());
		statement.setInt(20, player.getReachedOnError());
		statement.setInt(21, player.getRuns());
		statement.setInt(22, player.getRunsBattedIn());
		statement.setInt(23, player.getSacrificeBunts());
		statement.setInt(24, player.getSacrificeFlies());
		statement.setInt(25, player.getSingles());
		statement.setDouble(26, player.getSlugging());
		statement.setInt(27, player.getStolenBases());
		statement.setInt(28, player.getTotalBases());
		statement.setInt(29, player.getTriples());
		statement.setInt(30, player.getWalkOffs());
		statement.setInt(31, player.getWalkOffs());
		statement.setInt(32, 0);
		statement.setString(33, player.getFirstName());
		statement.setString(34, player.getLastName());
		statement.setInt(35, player.getStolenBaseAttempts());
		statement.executeUpdate();
		return player;
	    }
	    catch (Exception ex) {
		System.out.println(ex.getMessage());
		return null;
	    }
    	}
    }
    
    public static List<OffensivePlayer> createMultiplePlayers(List<OffensivePlayer> players, Connection connection) {
	
	for (OffensivePlayer player : players) {
	    if (player.getPreference().equals(BatterPreference.SWITCH)) {
		String sql = """
		INSERT INTO sp24.dd_offense_left (
		    id,
		    teamId,
		    atBats,
		    average,
		    caughtStealingPercentage,
		    doubles,
		    extraBaseHits,
		    gamesPlayed,
		    grandSlams,
		    groundIntoDoublePlay,
		    groundOutVsFlyOut,
		    hitByPitch,
		    hits,
		    homeruns,
		    intentionalWalks,
		    leftOnBase,
		    onBasePercentage,
		    onBasePlusSlugging,
		    plateAppearances,
		    reachedOnError,
		    runs,
		    runsBattedIn,
		    sacrificeBunts,
		    sacrificeFlies,
		    singles,
		    slugging,
		    stolenBases,
		    totalBases,
		    triples,
		    walks,
		    walkOffs,
		    ghosted_date,
		    firstName,
		    lastName, stolenBaseAttempts ) VALUES (
		    ?, ?, ?, ?, ?,
		    ?, ?, ?, ?, ?,
		    ?, ?, ?, ?, ?,
		    ?, ?, ?, ?, ?,
		    ?, ?, ?, ?, ?,
		    ?, ?, ?, ?, ?,
		    ?, ?, ?, ?, ? )
	    """;
		
		try(PreparedStatement statement = connection.prepareStatement(sql)) {
		    statement.setString(1, player.getId().toString());
		    statement.setString(2, player.getTeamId().toString());
		    statement.setInt(3, player.getAtBats());
		    statement.setDouble(4, player.getAverage());
		    statement.setDouble(5, player.getCaughtStealingPercentage());
		    statement.setInt(6, player.getDoubles());
		    statement.setInt(7, player.getExtraBaseHits());
		    statement.setInt(8, player.getGamesPlayed());
		    statement.setInt(9, player.getGrandSlams());
		    statement.setInt(10, player.getGroundIntoDoublePlay());
		    statement.setDouble(11, player.getGroundOutVsFlyOut());
		    statement.setInt(12, player.getHitByPitch());
		    statement.setInt(13, player.getHits());
		    statement.setInt(14, player.getHomeRuns());
		    statement.setInt(15, player.getIntentionalWalks());
		    statement.setInt(16, player.getLeftOnBase());
		    statement.setDouble(17, player.getOnBasePercentage());
		    statement.setDouble(18, player.getOnBasePlusSlugging());
		    statement.setInt(19, player.getPlateAppearances());
		    statement.setInt(20, player.getReachedOnError());
		    statement.setInt(21, player.getRuns());
		    statement.setInt(22, player.getRunsBattedIn());
		    statement.setInt(23, player.getSacrificeBunts());
		    statement.setInt(24, player.getSacrificeFlies());
		    statement.setInt(25, player.getSingles());
		    statement.setDouble(26, player.getSlugging());
		    statement.setInt(27, player.getStolenBases());
		    statement.setInt(28, player.getTotalBases());
		    statement.setInt(29, player.getTriples());
		    statement.setInt(30, player.getWalkOffs());
		    statement.setInt(31, player.getWalkOffs());
		    statement.setInt(32, 0);
		    statement.setString(33, player.getFirstName());
		    statement.setString(34, player.getLastName());
		    statement.setInt(35, player.getStolenBaseAttempts());
		    statement.executeUpdate();
		}
		catch (Exception ex) {
		    System.out.println(ex.getMessage());
		    return null;
		}
		
		sql = """
		    INSERT INTO sp24.dd_offense_right (
		    id,
		    teamId,
		    atBats,
		    average,
		    caughtStealingPercentage,
		    doubles,
		    extraBaseHits,
		    gamesPlayed,
		    grandSlams,
		    groundIntoDoublePlay,
		    groundOutVsFlyOut,
		    hitByPitch,
		    hits,
		    homeruns,
		    intentionalWalks,
		    leftOnBase,
		    onBasePercentage,
		    onBasePlusSlugging,
		    plateAppearances,
		    reachedOnError,
		    runs,
		    runsBattedIn,
		    sacrificeBunts,
		    sacrificeFlies,
		    singles,
		    slugging,
		    stolenBases,
		    totalBases,
		    triples,
		    walks,
		    walkOffs,
		    ghosted_date,
		    firstName,
		    lastName, stolenBaseAttempts ) VALUES (
		    ?, ?, ?, ?, ?,
		    ?, ?, ?, ?, ?,
		    ?, ?, ?, ?, ?,
		    ?, ?, ?, ?, ?,
		    ?, ?, ?, ?, ?,
		    ?, ?, ?, ?, ?,
		    ?, ?, ?, ?, ? )
	"""    ;
		
		try(PreparedStatement statement = connection.prepareStatement(sql)) {
		    statement.setString(1, player.getId().toString());
		    statement.setString(2, player.getTeamId().toString());
		    statement.setInt(3, player.getAtBats());
		    statement.setDouble(4, player.getAverage());
		    statement.setDouble(5, player.getCaughtStealingPercentage());
		    statement.setInt(6, player.getDoubles());
		    statement.setInt(7, player.getExtraBaseHits());
		    statement.setInt(8, player.getGamesPlayed());
		    statement.setInt(9, player.getGrandSlams());
		    statement.setInt(10, player.getGroundIntoDoublePlay());
		    statement.setDouble(11, player.getGroundOutVsFlyOut());
		    statement.setInt(12, player.getHitByPitch());
		    statement.setInt(13, player.getHits());
		    statement.setInt(14, player.getHomeRuns());
		    statement.setInt(15, player.getIntentionalWalks());
		    statement.setInt(16, player.getLeftOnBase());
		    statement.setDouble(17, player.getOnBasePercentage());
		    statement.setDouble(18, player.getOnBasePlusSlugging());
		    statement.setInt(19, player.getPlateAppearances());
		    statement.setInt(20, player.getReachedOnError());
		    statement.setInt(21, player.getRuns());
		    statement.setInt(22, player.getRunsBattedIn());
		    statement.setInt(23, player.getSacrificeBunts());
		    statement.setInt(24, player.getSacrificeFlies());
		    statement.setInt(25, player.getSingles());
		    statement.setDouble(26, player.getSlugging());
		    statement.setInt(27, player.getStolenBases());
		    statement.setInt(28, player.getTotalBases());
		    statement.setInt(29, player.getTriples());
		    statement.setInt(30, player.getWalkOffs());
		    statement.setInt(31, player.getWalkOffs());
		    statement.setInt(32, 0);
		    statement.setString(33, player.getFirstName());
		    statement.setString(34, player.getLastName());
		    statement.setInt(35, player.getStolenBaseAttempts());
		    statement.executeUpdate();
		}
		catch (Exception ex) {
		    System.out.println(ex.getMessage());
		    return null;
		}
	    }
	    
	    else {
		String sql = player.getPreference().equals(BatterPreference.LEFT) ?
		    """
		    INSERT INTO sp24.dd_offense_left (
			id,
			teamId,
			atBats,
			average,
			caughtStealingPercentage,
			doubles,
			extraBaseHits,
			gamesPlayed,
			grandSlams,
			groundIntoDoublePlay,
			groundOutVsFlyOut,
			hitByPitch,
			hits,
			homeruns,
			intentionalWalks,
			leftOnBase,
			onBasePercentage,
			onBasePlusSlugging,
			plateAppearances,
			reachedOnError,
			runs,
			runsBattedIn,
			sacrificeBunts,
			sacrificeFlies,
			singles,
			slugging,
			stolenBases,
			totalBases,
			triples,
			walks,
			walkOffs,
			ghosted_date,
			firstName,
			lastName, stolenBaseAttempts ) VALUES (
			?, ?, ?, ?, ?,
			?, ?, ?, ?, ?,
			?, ?, ?, ?, ?,
			?, ?, ?, ?, ?,
			?, ?, ?, ?, ?,
			?, ?, ?, ?, ?,
			?, ?, ?, ?, ? )
		    """
		    :
		    """
		       INSERT INTO sp24.dd_offense_right (
			id,
			teamId,
			atBats,
			average,
			caughtStealingPercentage,
			doubles,
			extraBaseHits,
			gamesPlayed,
			grandSlams,
			groundIntoDoublePlay,
			groundOutVsFlyOut,
			hitByPitch,
			hits,
			homeruns,
			intentionalWalks,
			leftOnBase,
			onBasePercentage,
			onBasePlusSlugging,
			plateAppearances,
			reachedOnError,
			runs,
			runsBattedIn,
			sacrificeBunts,
			sacrificeFlies,
			singles,
			slugging,
			stolenBases,
			totalBases,
			triples,
			walks,
			walkOffs,
			ghosted_date,
			firstName,
			lastName, stolenBaseAttempts ) VALUES (
			?, ?, ?, ?, ?,
			?, ?, ?, ?, ?,
			?, ?, ?, ?, ?,
			?, ?, ?, ?, ?,
			?, ?, ?, ?, ?,
			?, ?, ?, ?, ?,
			?, ?, ?, ?, ? )
	    """    ;
		
		try(PreparedStatement statement = connection.prepareStatement(sql)) {
		    statement.setString(1, player.getId().toString());
		    statement.setString(2, player.getTeamId().toString());
		    statement.setInt(3, player.getAtBats());
		    statement.setDouble(4, player.getAverage());
		    statement.setDouble(5, player.getCaughtStealingPercentage());
		    statement.setInt(6, player.getDoubles());
		    statement.setInt(7, player.getExtraBaseHits());
		    statement.setInt(8, player.getGamesPlayed());
		    statement.setInt(9, player.getGrandSlams());
		    statement.setInt(10, player.getGroundIntoDoublePlay());
		    statement.setDouble(11, player.getGroundOutVsFlyOut());
		    statement.setInt(12, player.getHitByPitch());
		    statement.setInt(13, player.getHits());
		    statement.setInt(14, player.getHomeRuns());
		    statement.setInt(15, player.getIntentionalWalks());
		    statement.setInt(16, player.getLeftOnBase());
		    statement.setDouble(17, player.getOnBasePercentage());
		    statement.setDouble(18, player.getOnBasePlusSlugging());
		    statement.setInt(19, player.getPlateAppearances());
		    statement.setInt(20, player.getReachedOnError());
		    statement.setInt(21, player.getRuns());
		    statement.setInt(22, player.getRunsBattedIn());
		    statement.setInt(23, player.getSacrificeBunts());
		    statement.setInt(24, player.getSacrificeFlies());
		    statement.setInt(25, player.getSingles());
		    statement.setDouble(26, player.getSlugging());
		    statement.setInt(27, player.getStolenBases());
		    statement.setInt(28, player.getTotalBases());
		    statement.setInt(29, player.getTriples());
		    statement.setInt(30, player.getWalkOffs());
		    statement.setInt(31, player.getWalkOffs());
		    statement.setInt(32, 0);
		    statement.setString(33, player.getFirstName());
		    statement.setString(34, player.getLastName());
		    statement.setInt(35, player.getStolenBaseAttempts());
		    statement.executeUpdate();
		}
		catch (Exception ex) {
		    System.out.println(ex.getMessage());
		    return null;
		}
	    }
	}
	
	return players;
    }
    
    public static OffensivePlayer editPlayer(UUID id, OffensivePlayer newModel, Connection connection) {
	String sql = """
	   UPDATE sp24.dd_offense_left SET
	   id = ?,
	   teamId = ?,
	   atBats = ?,
	   average = ?,
	   caughtStealingPercentage = ?,
	   doubles = ?,
	   extraBaseHits = ?,
	   gamesPlayed = ?,
	   grandSlams = ?,
	   groundIntoDoublePlay = ?,
	   groundOutVsFlyOut = ?,
	   hitByPitch = ?,
	   hits = ?,
	   homeruns = ?,
	   intentionalWalks = ?,
	   leftOnBase = ?,
	   onBasePercentage = ?,
	   onBasePlusSlugging = ?,
	   plateAppearances = ?,
	   reachedOnError = ?,
	   runs = ?,
	   runsBattedIn = ?,
	   sacrificeBunts = ?,
	   sacrificeFlies = ?,
	   singles = ?,
	   slugging = ?,
	   stolenBases = ?,
	   totalBases = ?,
	   triples = ?,
	   walks = ?,
	   walkOffs = ?,
	   ghosted_date = ?,
	   firstName = ?,
	   lastName = ?,
	   stolenBaseAttempts = ?
	   WHERE id = ?o
	""";
	
	try(PreparedStatement statement = connection.prepareStatement(sql)) {
	    statement.setString(1, id.toString());
	    statement.setString(2, newModel.getTeamId().toString());
	    statement.setInt(3, newModel.getAtBats());
	    statement.setDouble(4, newModel.getAverage());
	    statement.setDouble(5, newModel.getCaughtStealingPercentage());
	    statement.setInt(6, newModel.getDoubles());
	    statement.setInt(7, newModel.getExtraBaseHits());
	    statement.setInt(8, newModel.getGamesPlayed());
	    statement.setInt(9, newModel.getGrandSlams());
	    statement.setInt(10, newModel.getGroundIntoDoublePlay());
	    statement.setDouble(11, newModel.getGroundOutVsFlyOut());
	    statement.setInt(12, newModel.getHitByPitch());
	    statement.setInt(13, newModel.getHits());
	    statement.setInt(14, newModel.getHomeRuns());
	    statement.setInt(15, newModel.getIntentionalWalks());
	    statement.setInt(16, newModel.getLeftOnBase());
	    statement.setDouble(17, newModel.getOnBasePercentage());
	    statement.setDouble(18, newModel.getOnBasePlusSlugging());
	    statement.setInt(19, newModel.getPlateAppearances());
	    statement.setInt(20, newModel.getReachedOnError());
	    statement.setInt(21, newModel.getRuns());
	    statement.setInt(22, newModel.getRunsBattedIn());
	    statement.setInt(23, newModel.getSacrificeBunts());
	    statement.setInt(24, newModel.getSacrificeFlies());
	    statement.setInt(25, newModel.getSingles());
	    statement.setDouble(26, newModel.getSlugging());
	    statement.setInt(27, newModel.getStolenBases());
	    statement.setInt(28, newModel.getTotalBases());
	    statement.setInt(29, newModel.getTriples());
	    statement.setInt(30, newModel.getWalkOffs());
	    statement.setInt(31, newModel.getWalkOffs());
	    statement.setInt(32, 0);
	    statement.setString(33, newModel.getFirstName());
	    statement.setString(34, newModel.getLastName());
	    statement.setInt(35, newModel.getStolenBaseAttempts());
	    statement.setString(36, id.toString());
	    statement.executeUpdate();
	}
	catch (Exception ex) {
	    System.out.println(ex.getMessage());
	    return null;
	}
	
	sql = """
	   UPDATE sp24.dd_offense_right SET
	   id = ?,
	   teamId = ?,
	   atBats = ?,
	   average = ?,
	   caughtStealingPercentage = ?,
	   doubles = ?,
	   extraBaseHits = ?,
	   gamesPlayed = ?,
	   grandSlams = ?,
	   groundIntoDoublePlay = ?,
	   groundOutVsFlyOut = ?,
	   hitByPitch = ?,
	   hits = ?,
	   homeruns = ?,
	   intentionalWalks = ?,
	   leftOnBase = ?,
	   onBasePercentage = ?,
	   onBasePlusSlugging = ?,
	   plateAppearances = ?,
	   reachedOnError = ?,
	   runs = ?,
	   runsBattedIn = ?,
	   sacrificeBunts = ?,
	   sacrificeFlies = ?,
	   singles = ?,
	   slugging = ?,
	   stolenBases = ?,
	   totalBases = ?,
	   triples = ?,
	   walks = ?,
	   walkOffs = ?,
	   ghosted_date = ?,
	   firstName = ?,
	   lastName = ?,
	   stolenBaseAttempts = ?
	   WHERE id = ?
	""";
	
	try(PreparedStatement statement = connection.prepareStatement(sql)) {
	    statement.setString(1, id.toString());
	    statement.setString(2, newModel.getTeamId().toString());
	    statement.setInt(3, newModel.getAtBats());
	    statement.setDouble(4, newModel.getAverage());
	    statement.setDouble(5, newModel.getCaughtStealingPercentage());
	    statement.setInt(6, newModel.getDoubles());
	    statement.setInt(7, newModel.getExtraBaseHits());
	    statement.setInt(8, newModel.getGamesPlayed());
	    statement.setInt(9, newModel.getGrandSlams());
	    statement.setInt(10, newModel.getGroundIntoDoublePlay());
	    statement.setDouble(11, newModel.getGroundOutVsFlyOut());
	    statement.setInt(12, newModel.getHitByPitch());
	    statement.setInt(13, newModel.getHits());
	    statement.setInt(14, newModel.getHomeRuns());
	    statement.setInt(15, newModel.getIntentionalWalks());
	    statement.setInt(16, newModel.getLeftOnBase());
	    statement.setDouble(17, newModel.getOnBasePercentage());
	    statement.setDouble(18, newModel.getOnBasePlusSlugging());
	    statement.setInt(19, newModel.getPlateAppearances());
	    statement.setInt(20, newModel.getReachedOnError());
	    statement.setInt(21, newModel.getRuns());
	    statement.setInt(22, newModel.getRunsBattedIn());
	    statement.setInt(23, newModel.getSacrificeBunts());
	    statement.setInt(24, newModel.getSacrificeFlies());
	    statement.setInt(25, newModel.getSingles());
	    statement.setDouble(26, newModel.getSlugging());
	    statement.setInt(27, newModel.getStolenBases());
	    statement.setInt(28, newModel.getTotalBases());
	    statement.setInt(29, newModel.getTriples());
	    statement.setInt(30, newModel.getWalkOffs());
	    statement.setInt(31, newModel.getWalkOffs());
	    statement.setInt(32, 0);
	    statement.setString(33, newModel.getFirstName());
	    statement.setString(34, newModel.getLastName());
	    statement.setInt(35, newModel.getStolenBaseAttempts());
	    statement.setString(36, id.toString());
	    statement.executeUpdate();
	}
	catch (Exception ex) {
	    System.out.println(ex.getMessage());
	    return null;
	}
	
	return newModel;
    }
    
    public static List<OffensivePlayer> getAllOffensivePlayers(Connection connection) {
	String sql = "SELECT * FROM sp24.dd_offense_left WHERE ghosted_date=0 UNION SELECT * FROM sp24.dd_offense_right WHERE ghosted_date=0";
	OffensivePlayerBuilder builder = new OffensivePlayerBuilder();
	List<OffensivePlayer> players = new ArrayList<>();
	
	try(PreparedStatement statement = connection.prepareStatement(sql)) {
	    ResultSet set = statement.executeQuery();
	    while(set.next()) {
		players.add(
		    builder
			.setId(UUID.fromString(set.getString("id")))
			.setTeamId(UUID.fromString(set.getString("teamId")))
			.setAtBats(set.getInt("atBats"))
			.setAverage(set.getDouble("average"))
			.setCaughtStealingPercentage(set.getDouble("caughtStealingPercentage"))
			.setDoubles(set.getInt("doubles"))
			.setExtraBaseHits(set.getInt("extraBaseHits"))
			.setGamesPlayed(set.getInt("gamesPlayed"))
			.setGrandSlams(set.getInt("grandSlams"))
			.setGroundIntoDoublePlay(set.getInt("groundIntoDoublePlay"))
			.setGroundOutVsFlyOut(set.getDouble("groundOutVsFlyOut"))
			.setHitByPitch(set.getInt("hitByPitch"))
			.setHits(set.getInt("hits"))
			.setHomeRuns(set.getInt("homeruns"))
			.setIntentionalWalks(set.getInt("intentionalWalks"))
			.setLeftOnBase(set.getInt("leftOnBase"))
			.setOnBasePercentage(set.getInt("onBasePercentage"))
			.setOnBasePlusSlugging(set.getDouble("onBasePlusSlugging"))
			.setPlateAppearances(set.getInt("plateAppearances"))
			.setReachedOnError(set.getInt("reachedOnError"))
			.setRuns(set.getInt("runs"))
			.setRunsBattedIn(set.getInt("runsBattedIn"))
			.setSacrificeBunts(set.getInt("sacrificeBunts"))
			.setSacrificeFlies(set.getInt("sacrificeFlies"))
			.setSingles(set.getInt("singles"))
			.setSlugging(set.getDouble("slugging"))
			.setStolenBases(set.getInt("stolenBases"))
			.setTotalBases(set.getInt("totalBases"))
			.setTriples(set.getInt("triples"))
			.setWalks(set.getInt("walks"))
			.setWalkOffs(set.getInt("walkOffs"))
			.setFirstName(set.getString("firstName"))
			.setLastName(set.getString("lastName"))
			.setStolenBaseAttempts(set.getInt("stolenBaseAttempts"))
			.createOffensivePlayer()
		);
	    }
	}
	catch (Exception ex) {
	    System.out.println(ex.getMessage());
	}
	return players;
    }
    
    public static OffensivePlayer getOffensivePlayer(UUID id, Connection connection) {
	String sql =
	    "SELECT * FROM sp24.dd_offense_left WHERE ghosted_date=0 AND id=? UNION SELECT * FROM sp24.dd_offense_right WHERE ghosted_date=0 AND id=?";
	OffensivePlayerBuilder builder = new OffensivePlayerBuilder();
	
	try(PreparedStatement statement = connection.prepareStatement(sql)) {
	    statement.setString(1, id.toString());
	    statement.setString(2, id.toString());
	    
	    ResultSet set = statement.executeQuery();
	    while(set.next()) {
		return
		    builder
			.setId(UUID.fromString(set.getString("id")))
			.setTeamId(UUID.fromString(set.getString("teamId")))
			.setAtBats(set.getInt("atBats"))
			.setAverage(set.getDouble("average"))
			.setCaughtStealingPercentage(set.getDouble("caughtStealingPercentage"))
			.setDoubles(set.getInt("doubles"))
			.setExtraBaseHits(set.getInt("extraBaseHits"))
			.setGamesPlayed(set.getInt("gamesPlayed"))
			.setGrandSlams(set.getInt("grandSlams"))
			.setGroundIntoDoublePlay(set.getInt("groundIntoDoublePlay"))
			.setGroundOutVsFlyOut(set.getDouble("groundOutVsFlyOut"))
			.setHitByPitch(set.getInt("hitByPitch"))
			.setHits(set.getInt("hits"))
			.setHomeRuns(set.getInt("homeruns"))
			.setIntentionalWalks(set.getInt("intentionalWalks"))
			.setLeftOnBase(set.getInt("leftOnBase"))
			.setOnBasePercentage(set.getInt("onBasePercentage"))
			.setOnBasePlusSlugging(set.getDouble("onBasePlusSlugging"))
			.setPlateAppearances(set.getInt("plateAppearances"))
			.setReachedOnError(set.getInt("reachedOnError"))
			.setRuns(set.getInt("runs"))
			.setRunsBattedIn(set.getInt("runsBattedIn"))
			.setSacrificeBunts(set.getInt("sacrificeBunts"))
			.setSacrificeFlies(set.getInt("sacrificeFlies"))
			.setSingles(set.getInt("singles"))
			.setSlugging(set.getDouble("slugging"))
			.setStolenBases(set.getInt("stolenBases"))
			.setTotalBases(set.getInt("totalBases"))
			.setTriples(set.getInt("triples"))
			.setWalks(set.getInt("walks"))
			.setWalkOffs(set.getInt("walkOffs"))
			.setFirstName(set.getString("firstName"))
			.setLastName(set.getString("lastName"))
			.setStolenBaseAttempts(set.getInt("stolenBaseAttempts"))
			.createOffensivePlayer();
	    }
	}
	catch (Exception ex) {
	    System.out.println(ex.getMessage());
	    return null;
	}
	
	return null;
    }
    
    public static List<OffensivePlayer> getAllForTeam(UUID teamId, Connection connection) {
	String sql = "SELECT * FROM sp24.dd_offense_left WHERE ghosted_date=0 AND teamId=? UNION SELECT * FROM sp24.dd_offense_right WHERE ghosted_date=0 AND teamId=?";
	OffensivePlayerBuilder builder = new OffensivePlayerBuilder();
	List<OffensivePlayer> players = new ArrayList<>();
	
	try(PreparedStatement statement = connection.prepareStatement(sql)) {
	    statement.setString(1, teamId.toString());
	    statement.setString(2, teamId.toString());
	    ResultSet set = statement.executeQuery();
	    while(set.next()) {
		players.add(
		    builder
			.setId(UUID.fromString(set.getString("id")))
			.setTeamId(UUID.fromString(set.getString("teamId")))
			.setAtBats(set.getInt("atBats"))
			.setAverage(set.getDouble("average"))
			.setCaughtStealingPercentage(set.getDouble("caughtStealingPercentage"))
			.setDoubles(set.getInt("doubles"))
			.setExtraBaseHits(set.getInt("extraBaseHits"))
			.setGamesPlayed(set.getInt("gamesPlayed"))
			.setGrandSlams(set.getInt("grandSlams"))
			.setGroundIntoDoublePlay(set.getInt("groundIntoDoublePlay"))
			.setGroundOutVsFlyOut(set.getDouble("groundOutVsFlyOut"))
			.setHitByPitch(set.getInt("hitByPitch"))
			.setHits(set.getInt("hits"))
			.setHomeRuns(set.getInt("homeruns"))
			.setIntentionalWalks(set.getInt("intentionalWalks"))
			.setLeftOnBase(set.getInt("leftOnBase"))
			.setOnBasePercentage(set.getInt("onBasePercentage"))
			.setOnBasePlusSlugging(set.getDouble("onBasePlusSlugging"))
			.setPlateAppearances(set.getInt("plateAppearances"))
			.setReachedOnError(set.getInt("reachedOnError"))
			.setRuns(set.getInt("runs"))
			.setRunsBattedIn(set.getInt("runsBattedIn"))
			.setSacrificeBunts(set.getInt("sacrificeBunts"))
			.setSacrificeFlies(set.getInt("sacrificeFlies"))
			.setSingles(set.getInt("singles"))
			.setSlugging(set.getDouble("slugging"))
			.setStolenBases(set.getInt("stolenBases"))
			.setTotalBases(set.getInt("totalBases"))
			.setTriples(set.getInt("triples"))
			.setWalks(set.getInt("walks"))
			.setWalkOffs(set.getInt("walkOffs"))
			.setFirstName(set.getString("firstName"))
			.setLastName(set.getString("lastName"))
			.setStolenBaseAttempts(set.getInt("stolenBaseAttempts"))
			.createOffensivePlayer()
		);
	    }
	}
	catch (Exception ex) {
	    System.out.println(ex.getMessage());
	}
	return players;
    }
    
    public static List<OffensivePlayer> audit(Connection connection) {
	String sql = "SELECT * FROM sp24.dd_offense_left UNION SELECT * FROM sp24.dd_offense_right";
	OffensivePlayerBuilder builder = new OffensivePlayerBuilder();
	List<OffensivePlayer> players = new ArrayList<>();
	
	try(PreparedStatement statement = connection.prepareStatement(sql)) {
	    ResultSet set = statement.executeQuery();
	    while(set.next()) {
		players.add(
		    builder
			.setId(UUID.fromString(set.getString("id")))
			.setTeamId(UUID.fromString(set.getString("teamId")))
			.setAtBats(set.getInt("atBats"))
			.setAverage(set.getDouble("average"))
			.setCaughtStealingPercentage(set.getDouble("caughtStealingPercentage"))
			.setDoubles(set.getInt("doubles"))
			.setExtraBaseHits(set.getInt("extraBaseHits"))
			.setGamesPlayed(set.getInt("gamesPlayed"))
			.setGrandSlams(set.getInt("grandSlams"))
			.setGroundIntoDoublePlay(set.getInt("groundIntoDoublePlay"))
			.setGroundOutVsFlyOut(set.getDouble("groundOutVsFlyOut"))
			.setHitByPitch(set.getInt("hitByPitch"))
			.setHits(set.getInt("hits"))
			.setHomeRuns(set.getInt("homeruns"))
			.setIntentionalWalks(set.getInt("intentionalWalks"))
			.setLeftOnBase(set.getInt("leftOnBase"))
			.setOnBasePercentage(set.getInt("onBasePercentage"))
			.setOnBasePlusSlugging(set.getDouble("onBasePlusSlugging"))
			.setPlateAppearances(set.getInt("plateAppearances"))
			.setReachedOnError(set.getInt("reachedOnError"))
			.setRuns(set.getInt("runs"))
			.setRunsBattedIn(set.getInt("runsBattedIn"))
			.setSacrificeBunts(set.getInt("sacrificeBunts"))
			.setSacrificeFlies(set.getInt("sacrificeFlies"))
			.setSingles(set.getInt("singles"))
			.setSlugging(set.getDouble("slugging"))
			.setStolenBases(set.getInt("stolenBases"))
			.setTotalBases(set.getInt("totalBases"))
			.setTriples(set.getInt("triples"))
			.setWalks(set.getInt("walks"))
			.setWalkOffs(set.getInt("walkOffs"))
			.setFirstName(set.getString("firstName"))
			.setLastName(set.getString("lastName"))
			.setStolenBaseAttempts(set.getInt("stolenBaseAttempts"))
			.createOffensivePlayer()
		);
	    }
	}
	catch (Exception ex) {
	    System.out.println(ex.getMessage());
	}
	return players;
    }
    
    public static boolean deletePlayer(UUID id, Connection connection) {
	String sql = "UPDATE sp24.dd_offense_left SET ghosted_date=? WHERE id=? ; UPDATE sp24.dd_offense_right SET ghosted_date=? WHERE id=?";
	TruncatedSystemTimeProvider provider = new TruncatedSystemTimeProvider();
	try(PreparedStatement statement = connection.prepareStatement(sql)) {
	    statement.setLong(1, provider.provideTime());
	    statement.setString(2, id.toString());
	    statement.setLong(3, provider.provideTime());
	    statement.setString(4, id.toString());
	    statement.executeUpdate();
	    return true;
	}
	catch (Exception ex) {
	    System.out.println(ex.getMessage());
	    return false;
	}
    }
    
    public static boolean deleteAllPlayersOnTeam(UUID teamId, Connection connection) {
	String sql = "UPDATE sp24.dd_offense_left SET ghosted_date=? WHERE teamId=? ; UPDATE sp24.dd_offense_right SET ghosted_date=? WHERE teamId=?";
	TruncatedSystemTimeProvider provider = new TruncatedSystemTimeProvider();
	try(PreparedStatement statement = connection.prepareStatement(sql)) {
	    statement.setLong(1, provider.provideTime());
	    statement.setString(2, teamId.toString());
	    statement.setLong(3, provider.provideTime());
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