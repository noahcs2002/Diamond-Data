package com.dd.api.restapi.controllers;

import com.dd.api.database.Context;
import com.dd.api.restapi.factories.OffensivePlayerFactory;
import com.dd.api.restapi.models.OffensivePlayer;

import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/diamond-data/api/offensive-players")
public class OffensivePlayerController {
    
    private final Context context = new Context();
    
    /**
     * Test endpoint to establish connection to controller
     * @return A status message.
     */
    @GetMapping
    @RequestMapping("xyz")
    public String connect() {
	return "OffensivePlayerController Online";
    }
    
    /**
     * Get all offensive players
     * @return A list of all players
     */
    @GetMapping
    public List<OffensivePlayer> getAllOffensivePlayers() {
        try(Connection connection = DriverManager.getConnection(context.getConnectionString(), context.getUsername(), context.getPassword())) {
            return OffensivePlayerFactory.getAllOffensivePlayers(connection);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    /**
     * Get an offensive player by an ID
     * @param id The ID to search for
     * @return The player found.
     */
    @GetMapping
    @RequestMapping("/get-by-id")
    public OffensivePlayer getOffensivePlayer(@RequestParam UUID id) {
        try(Connection connection = DriverManager.getConnection(context.getConnectionString(), context.getUsername(), context.getPassword())) {
            return OffensivePlayerFactory.getOffensivePlayer(id, connection);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    /**
     * Get all players on a team
     * @param teamId The id of the team to get for
     * @return List of players on the team
     */
    @GetMapping
    @RequestMapping("/get-by-team")
    public List<OffensivePlayer> getOffensivePlayersByTeam(@RequestParam UUID teamId) {
        try(Connection connection = DriverManager.getConnection(context.getConnectionString(), context.getUsername(), context.getPassword())) {
            return OffensivePlayerFactory.getAllForTeam(teamId, connection);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    @GetMapping
    @RequestMapping("/audit")
    public List<OffensivePlayer> audit() {
        try(Connection connection = DriverManager.getConnection(context.getConnectionString(), context.getUsername(), context.getPassword())) {
            return OffensivePlayerFactory.audit(connection);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    /**
     * Create a new player
     * @param player The model to persist
     * @return The player that has been persisted
     */
    @PostMapping
    public OffensivePlayer create(@RequestBody OffensivePlayer player) {
        try(Connection connection = DriverManager.getConnection(context.getConnectionString(), context.getUsername(), context.getPassword())) {
            return OffensivePlayerFactory.createPlayer(player, connection);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    /**
     * Utility Endpoint for creating multiple players at once
     * @param players List of players to persist
     * @return The list of players that has been persisted
     */
    @PostMapping
    @RequestMapping("/create-multiple")
    public List<OffensivePlayer> createMultiple(@RequestBody List<OffensivePlayer> players) {
        try(Connection connection = DriverManager.getConnection(context.getConnectionString(), context.getUsername(), context.getPassword())) {
            return OffensivePlayerFactory.createMultiplePlayers(players, connection);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    /**
     * Edit a player
     * @param id The id of the player to edit
     * @param newModel The new model to make the player match
     * @return The new player
     */
    @PutMapping
    public OffensivePlayer edit(@RequestParam UUID id, @RequestBody OffensivePlayer newModel) {
        try(Connection connection = DriverManager.getConnection(context.getConnectionString(), context.getUsername(), context.getPassword())) {
            return OffensivePlayerFactory.editPlayer(id, newModel, connection);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    /**
     * Delete a player
     * @param id The ID of the player to delete
     * @return true if successful, else false
     */
    @DeleteMapping
    public boolean delete(@RequestParam UUID id) {
        try(Connection connection = DriverManager.getConnection(context.getConnectionString(), context.getUsername(), context.getPassword())) {
            return OffensivePlayerFactory.deletePlayer(id, connection);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    /**
     * Delete all players by team
     * @param teamId The team ID to delete all players for
     * @return true if successful, else false
     */
    @DeleteMapping
    @RequestMapping("/del-by-team")
    public boolean deleteAllForTeam(@RequestParam UUID teamId) {
        try(Connection connection = DriverManager.getConnection(context.getConnectionString(), context.getUsername(), context.getPassword())) {
            return OffensivePlayerFactory.deleteAllPlayersOnTeam(teamId, connection);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}