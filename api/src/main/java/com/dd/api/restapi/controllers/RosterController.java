package com.dd.api.restapi.controllers;

import com.dd.api.database.Context;
import com.dd.api.restapi.factories.RosterFactory;
import com.dd.api.restapi.models.Roster;
import com.dd.api.restapi.requestmodels.CreateRosterRequest;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

@RestController
@RequestMapping("/diamond-data/api/rosters")
public class RosterController {
    
    @GetMapping
    @RequestMapping("xyz")
    public String connect() {
	return "RosterController Online";
    }
    
    @PostMapping
    public Roster createRoster(@RequestBody CreateRosterRequest rosterRequest) {
	Context context = new Context();
	try(Connection connection = DriverManager.getConnection(context.getConnectionString(), context.getUsername(), context.getPassword())) {
	    return RosterFactory.createRoster(rosterRequest, connection);
	}
	catch (Exception ex) {
	    System.out.println(ex);
	    return null;
	}
    }
    
}
