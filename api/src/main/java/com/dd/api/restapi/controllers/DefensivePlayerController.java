package com.dd.api.restapi.controllers;

import com.dd.api.database.Context;
import com.dd.api.restapi.factories.DefensivePlayerFactory;
import com.dd.api.restapi.models.DefensivePlayer;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/diamond-data/api/defensive-players")
public class DefensivePlayerController {
    
    private final Context context = new Context();
    
    @GetMapping
    @RequestMapping("xyz")
    public String connect() {
	return "DefensivePlayerController Online";
    }
    
    @GetMapping
    @RequestMapping("/by-id")
    public DefensivePlayer getPlayer(@RequestParam UUID playerId) {
	try {
	    Connection connection = DriverManager.getConnection(context.getConnectionString(), context.getUsername(), context.getPassword());
	    return DefensivePlayerFactory.getPlayerById(playerId, connection);
	}
	catch (Exception ex) {
	    System.out.println(ex.getMessage());
	   return null;
	}
    }
    
    @GetMapping
    public List<DefensivePlayer> getAll() {
	try {
	    Connection connection = DriverManager.getConnection(context.getConnectionString(), context.getUsername(), context.getPassword());
	    return DefensivePlayerFactory.getAll(connection);
	}
	catch (Exception ex) {
	    return List.of();
	}
    }
    
    @GetMapping
    @RequestMapping("/by-team")
    public List<DefensivePlayer> getPlayersByTeam(@RequestParam UUID teamId) {
	try {
	    Connection connection = DriverManager.getConnection(context.getConnectionString(), context.getUsername(), context.getPassword());
	    return DefensivePlayerFactory.getByTeam(teamId, connection);
	}
	catch (Exception ex) {
	    return List.of();
	}
    }
    
    @PostMapping
    public boolean createPlayer(@RequestBody DefensivePlayer player) {
	try {
	    Connection connection = DriverManager.getConnection(context.getConnectionString(), context.getUsername(), context.getPassword());
	    return DefensivePlayerFactory.createPlayer(player, connection);
	}
	catch (Exception ex) {
	    return false;
	}
    }
    
    /**
     * Pass in the player's ID and the most up-to-date model of their statistics, and it will be
     * updated within persistence.
     * @param playerId The ID of the player you wish to modify.
     * @param player The model that you want to update the player to.
     * @return An updated player model
     */
    @PutMapping
    public DefensivePlayer updatePlayer(@RequestParam UUID playerId, @RequestBody DefensivePlayer player) {
	try {
	    Connection connection = DriverManager.getConnection(context.getConnectionString(), context.getUsername(), context.getPassword());
	    return DefensivePlayerFactory.update(playerId, player, connection);
	}
	catch (Exception ex) {
	    return null;
	}
    }
}
