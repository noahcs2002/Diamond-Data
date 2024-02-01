package com.dd.api.restapi.controllers;

import com.dd.api.restapi.models.OffensivePlayer;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/diamond-data/api/offensive-players")
public class OffensivePlayerController {
    
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
    @RequestMapping("/all")
    public List<OffensivePlayer> getAllOffensivePlayers() {
	return List.of();
    }
    
    /**
     * Get an offensive player by an ID
     * @param id The ID to search for
     * @return The player found.
     */
    @GetMapping
    @RequestMapping("/by-id")
    public OffensivePlayer getOffensivePlayer(@RequestParam UUID id) {
	return null;
    }
    
    /**
     * Get all players on a team
     * @param teamId The id of the team to get for
     * @return List of players on the team
     */
    @GetMapping
    @RequestMapping("/by-team")
    public List<OffensivePlayer> getOffensivePlayersByTeam(@RequestParam UUID teamId) {
	return List.of();
    }
    
    /**
     * Utility endpoint for collecting multiple offensive players at once
     * @param ids The list of ids you wish to collect
     * @return
     */
    @GetMapping
    @RequestMapping("/get-multiple")
    public List<OffensivePlayer> getMultipleOffensivePlayers(@RequestBody List<UUID> ids) {
	return List.of();
    }
    
    /**
     * Auditing endpoint for collecting all records in the table
     * regardless of auditing status
     * @return All records in the table
     */
    @GetMapping
    @RequestMapping("/audit")
    public List<OffensivePlayer> audit() {
        return List.of();
    }
    
    /**
     * Create a new player
     * @param player The model to persist
     * @return The player that has been persisted
     */
    @PostMapping
    public OffensivePlayer create(@RequestBody OffensivePlayer player) {
        return null;
    }
    
    /**
     * Utility Endpoint for creating multiple players at once
     * @param players List of players to persist
     * @return The list of players that has been persisted
     */
    @PostMapping
    @RequestMapping("/create-multiple")
    public List<OffensivePlayer> createMultiple(@RequestBody List<OffensivePlayer> players) {
        return List.of();
    }
    
    /**
     * Edit a player
     * @param id The id of the player to edit
     * @param newModel The new model to make the player match
     * @return The new player
     */
    @PutMapping
    public OffensivePlayer edit(@RequestParam UUID id, @RequestBody OffensivePlayer newModel) {
        return null;
    }
    
    /**
     * Delete a player
     * @param id The ID of the player to delete
     * @return true if successful, else false
     */
    @DeleteMapping
    public boolean delete(@RequestParam UUID id) {
        return false;
    }
    
    /**
     * Delete all players by team
     * @param teamId The team ID to delete all players for
     * @return true if successful, else false
     */
    @DeleteMapping
    @RequestMapping("/by-team")
    public boolean deleteAllForTeam(@RequestParam UUID teamId) {
        return false;
    }
}