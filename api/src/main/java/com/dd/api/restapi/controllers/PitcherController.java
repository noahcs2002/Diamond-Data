package com.dd.api.restapi.controllers;

import com.dd.api.database.Context;
import com.dd.api.restapi.factories.PitcherFactory;
import com.dd.api.restapi.models.Pitcher;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/diamond-data/api/pitchers")
public class PitcherController {

    private final Context context = new Context();

    @GetMapping
    @RequestMapping("xyz")
    public String connect() {
        return "PitcherController Online";
    }

    @PostMapping
    public Pitcher createPitcher(@RequestBody Pitcher pitcher) {
        try (Connection connection = DriverManager.getConnection(context.getConnectionString(), context.getUsername(), context.getPassword())) {
            return PitcherFactory.createPitcher(pitcher, connection);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    @GetMapping
    @RequestMapping("/by-id")
    // Returns as a list in case there is a switch pitcher.
    public List<Pitcher> getPitcherById(@RequestParam UUID id) {
        try (Connection connection = DriverManager.getConnection(context.getConnectionString(), context.getUsername(), context.getPassword())) {
            return PitcherFactory.getPitcherById(id, connection);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return List.of();
        }
    }
    
    @GetMapping
    @RequestMapping("/by-team")
    public List<Pitcher> getPitcherByTeam(@RequestParam UUID teamId) {
        try (Connection connection = DriverManager.getConnection(context.getConnectionString(), context.getUsername(), context.getPassword())) {
            return PitcherFactory.getPitchersByTeam(teamId, connection);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return List.of();
        }
    }
    
    @PutMapping
    @RequestMapping("/edit")
    public Pitcher editPitcher(@RequestParam UUID id, @RequestBody Pitcher newModel) {
        try (Connection connection = DriverManager.getConnection(context.getConnectionString(), context.getUsername(), context.getPassword())) {
            return PitcherFactory.editPitcher(id, newModel, connection);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    @DeleteMapping
    @RequestMapping("/del")
    public boolean deletePitcher(@RequestParam UUID id) {
        try (Connection connection = DriverManager.getConnection(context.getConnectionString(), context.getUsername(), context.getPassword())) {
            return PitcherFactory.deletePitcher(id, connection);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    @DeleteMapping
    @RequestMapping("/del-by-team")
    public boolean deleteAllPitchersForTeam(@RequestParam UUID teamId) {
        try (Connection connection = DriverManager.getConnection(context.getConnectionString(), context.getUsername(), context.getPassword())) {
            return PitcherFactory.deleteTeamPitchers(teamId, connection);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}