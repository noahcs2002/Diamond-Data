package com.dd.api.restapi.controllers;

import com.dd.api.database.Context;
import com.dd.api.restapi.factories.TeamFactory;
import com.dd.api.restapi.models.Team;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/diamond-data/api/teams")
public class TeamController {

    private final Context context = new Context();

    /**
     * Testing endpoint
     * @return Testing confirmation.
     */
    @GetMapping
    @RequestMapping("xyz")
    public String connect() {
        return "Team Controller Online";
    }

    /**
     * Get a team by ID
     * @param teamId The ID to get the team of.
     * @return The team.
     * @author noahsternberg
     */
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

    /**
     * Get all teams for a certain account.
     * @param accountId The id of the account to get teams for.
     * @return A list of teams pertaining 
     */
    @GetMapping
    @RequestMapping("/by-account")
    public List<Team> getTeamsByAccount(@RequestParam UUID accountId) {
        try{
            Connection connection = DriverManager.getConnection(context.getConnectionString(), context.getUsername(), context.getPassword());
            return TeamFactory.getTeamByAccountId(accountId, connection);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    /**
     * Get all teams in persistence.
     * @return List of teams.
     */
    @GetMapping
    @RequestMapping("/all")
    public List<Team> getAllTeams() {
        try{
            Connection connection = DriverManager.getConnection(context.getConnectionString(), context.getUsername(), context.getPassword());
            return TeamFactory.getAllTeams(connection);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    /**
     * Create a team
     * @param team The team to make
     * @return The team made.
     */
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

    /**
     * Mark a team as deleted in persistence.
     * @param id The ID of the team to delete.
     * @return true if successful, else false.
     */
    @DeleteMapping
    @RequestMapping("/del")
    public boolean deleteTeam(@RequestParam UUID id) {
        try{
            Connection connection = DriverManager.getConnection(context.getConnectionString(), context.getUsername(), context.getPassword());
            return TeamFactory.delete(id, connection);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    /**
     * Edit a team in persistence.
     * @param id The ID of the team to edit.
     * @param team The new model to make the team reflect.
     * @return the updated team.
     */
    @PutMapping
    @RequestMapping("/edit")
    public Team edit(@RequestParam UUID id, @RequestBody Team team) {
        try{
            Connection connection = DriverManager.getConnection(context.getConnectionString(), context.getUsername(), context.getPassword());
            return TeamFactory.edit(id, team, connection);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
