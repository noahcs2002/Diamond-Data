package com.dd.api.restapi.controllers;

import com.dd.api.database.Context;
import com.dd.api.restapi.factories.RosterFactory;
import com.dd.api.restapi.models.Roster;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;

@RestController
@RequestMapping("/diamond-data/api/rosters")
public class RosterController {
    
    @GetMapping
    @RequestMapping("xyz")
    public String connect() {
	return "RosterController Online";
    }
    
    @GetMapping
    @RequestMapping("/get-ai-roster")
    public Roster getAiRoster() {
        Context context = new Context();
        try(Connection connection = DriverManager.getConnection(context.getConnectionString(), context.getUsername(), context.getPassword())) {
            return RosterFactory.getAiRoster(connection);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}