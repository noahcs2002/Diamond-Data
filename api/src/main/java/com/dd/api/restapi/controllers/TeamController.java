package com.dd.api.restapi.controllers;

import com.dd.api.database.Context;
import com.dd.api.restapi.factories.TeamFactory;
import com.dd.api.restapi.models.Team;
import org.springframework.web.bind.annotation.*;

import java.awt.dnd.DropTarget;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.UUID;

@RestController
@RequestMapping("/diamond-data/api/teams")
public class TeamController {

    private final Context context = new Context();
    
    @GetMapping
    @RequestMapping("xyz")
    public String connect() {
        return "Team Controller Online";
    }

    @GetMapping
    @RequestMapping("/by-id")
    public Team getTeam(@RequestParam UUID teamId) {
        try{
            Connection connection = DriverManager.getConnection(context.getConnectionString(), context.getUsername(), context.getPassword());
            return TeamFactory.getTeam(teamId, connection);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @PostMapping
    @RequestMapping("/no-id")
    public Team createTeam(@RequestBody Team team) {
        try{
            Connection connection = DriverManager.getConnection(context.getConnectionString(), context.getUsername(), context.getPassword());
            return TeamFactory.createTeam(team, connection);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
