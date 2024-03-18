package com.dd.api.restapi.controllers;

import com.dd.api.restapi.models.Team;
import com.dd.api.restapi.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/diamond-data/api/teams")
public class TeamController {

    private final TeamService service;

    @Autowired
    public TeamController(TeamService service) {
        this.service = service;
    }

    @RequestMapping("/get")
    @GetMapping
    public Team get(@RequestParam Long id) {
        Objects.requireNonNull(id);
        return this.service.getTeamById(id);
    }

    @RequestMapping("/create")
    @PostMapping
    public Team create(@RequestBody Team team) {
        Objects.requireNonNull(team);
        return this.service.createTeam(team);
    }

    @RequestMapping("/get-all")
    @GetMapping
    public List<Team> getAll() {
        return this.service.getAllTeams();
    }

    @RequestMapping("/update")
    @PutMapping
    public Team update(@RequestParam Long id, @RequestBody Team newTeam) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(newTeam);
        return this.service.updateTeam(id, newTeam);
    }

    @RequestMapping("/delete")
    @DeleteMapping
    public boolean delete(@RequestParam Long id) {
        Objects.requireNonNull(id);
        return this.service.delete(id);
    }
}