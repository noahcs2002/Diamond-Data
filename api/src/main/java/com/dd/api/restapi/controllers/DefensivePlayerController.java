package com.dd.api.restapi.controllers;

import com.dd.api.database.Context;
import com.dd.api.restapi.factories.DefensivePlayerFactory;
import com.dd.api.restapi.models.DefensivePlayer;
import com.dd.api.util.TruncatedSystemTimeProvider;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/diamond-data/api/defensive-players")
public class DefensivePlayerController {
    
    // Simple settings context.
    private final Context sqlServerContext = new Context();
    
    /**
     * Testing method to allow developers to check that the service is running correctly.
     *
     * @return A string validating API connection.
     * @author noahsternberg
     */
    @GetMapping
    @RequestMapping("xyz")
    public String connect() {
	return "DefensivePlayerController Online";
    }
    
    /**
     * Get a player by their ID.
     *
     * @param playerId The ID of whom you wish to capture.
     * @return The player you have requested with the specified ID.
     * @author noahsternberg
     */
    @GetMapping
    @RequestMapping("/by-id")
    public DefensivePlayer getPlayer(@RequestParam UUID playerId) {
	try (Connection connection = DriverManager.getConnection(sqlServerContext.getConnectionString(), sqlServerContext.getUsername(), sqlServerContext.getPassword());){
	    return DefensivePlayerFactory.getPlayerById(playerId, connection);
	}
	catch (Exception ex) {
	    System.out.println(ex.getMessage());
	    return null;
	}
    }
    
    /**
     * Get all players logged in the database.
     *
     * @return A list of all players in the database.
     * @author noahsternberg
     */
    @GetMapping
    public List<DefensivePlayer> getAll() {
	try (Connection connection = DriverManager.getConnection(sqlServerContext.getConnectionString(), sqlServerContext.getUsername(), sqlServerContext.getPassword())){
	    return DefensivePlayerFactory.getAll(connection);
	}
	catch (Exception ex) {
	    return List.of();
	}
    }
    
    /**
     * Get all players on a specific team.
     *
     * @param teamId The team whom you are querying for players.
     * @return A list of all players on the specified team.
     * @author noahsternberg
     */
    @GetMapping
    @RequestMapping("/by-team")
    public List<DefensivePlayer> getPlayersByTeam(@RequestParam UUID teamId) {
	try (Connection connection = DriverManager.getConnection(sqlServerContext.getConnectionString(), sqlServerContext.getUsername(), sqlServerContext.getPassword())){
	    return DefensivePlayerFactory.getByTeam(teamId, connection);
	}
	catch (Exception ex) {
	    return List.of();
	}
    }
    
    /**
     * Create a player
     *
     * @param player The player to create in persistence.
     * @return True if the player is made successfully, else false.
     * @author noahsternberg
     */
    @PostMapping
    public boolean createPlayer(@RequestBody DefensivePlayer player) {
	try (Connection connection = DriverManager.getConnection(sqlServerContext.getConnectionString(), sqlServerContext.getUsername(), sqlServerContext.getPassword())){
	    return DefensivePlayerFactory.createPlayer(player, connection);
	}
	catch (Exception ex) {
	    return false;
	}
    }
    
    /**
     * Pass in the player's ID and the most up-to-date model of their statistics, and it will be
     * updated within persistence.
     *
     * @param playerId The ID of the player you wish to modify.
     * @param player   The model that you want to update the player to.
     * @return An updated player model
     * @author noahsternberg
     */
    @PutMapping
    @RequestMapping("update")
    public DefensivePlayer updatePlayer(@RequestParam UUID playerId, @RequestBody DefensivePlayer player) {
	try (Connection connection = DriverManager.getConnection(sqlServerContext.getConnectionString(), sqlServerContext.getUsername(), sqlServerContext.getPassword())){
	    return DefensivePlayerFactory.update(playerId, player, connection);
	}
	catch (Exception ex) {
	    return null;
	}
    }
    
    /**
     * Mark a player as deleted in the database.
     *
     * @param playerId The ID of whom you wish to delete.
     * @return True if successful, else false.
     * @apiNote Soft delete. This does not remove data from the database.
     */
    @DeleteMapping
    @RequestMapping("del")
    public boolean deleteUser(@RequestParam UUID playerId) {
	try (Connection connection = DriverManager.getConnection(sqlServerContext.getConnectionString(), sqlServerContext.getUsername(), sqlServerContext.getPassword())){
	    return DefensivePlayerFactory.deletePlayer(playerId, new TruncatedSystemTimeProvider(), connection);
	}
	catch (Exception ex) {
	    return false;
	}
    }
}