package com.dd.api.restapi.controllers;

import com.dd.api.database.Context;
import com.dd.api.restapi.factories.DefensivePlayerFactory;
import com.dd.api.restapi.models.DefensivePlayer;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
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
    
    @PostMapping
    @RequestMapping("/create")
    public boolean createPlayer(@RequestBody DefensivePlayer player) {
	try {
	    Connection connection = DriverManager.getConnection(context.getConnectionString(), context.getUsername(), context.getPassword());
	    return DefensivePlayerFactory.createPlayer(player, connection);
	}
	catch (Exception ex) {
	    return false;
	}
    }
}
