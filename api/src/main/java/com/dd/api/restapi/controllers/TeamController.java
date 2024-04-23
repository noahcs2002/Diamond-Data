package com.dd.api.restapi.controllers;

import com.dd.api.auth.validators.Validator;
import com.dd.api.restapi.models.Team;
import com.dd.api.restapi.services.TeamService;
import com.dd.api.util.exceptions.NoAccessPermittedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/diamond-data/api/teams")
public class TeamController {

    @Autowired
    private final TeamService service;

    @Autowired
    private final Validator validator;

    @Autowired
    public TeamController(TeamService service, Validator validator) {
        this.service = service;
        this.validator = validator;
    }

    @RequestMapping("/get")
    @GetMapping
    public Team get(@RequestParam Long id, @RequestParam Long userId) throws NoAccessPermittedException {
        Objects.requireNonNull(id);
        Objects.requireNonNull(userId);

        if(!this.validator.validateTeam(userId, id)) {
            throw new NoAccessPermittedException(userId);
        }

        return this.service.getTeamById(id);
    }

    @RequestMapping("/get-by-user")
    @GetMapping
    public Team getByUser(@RequestParam Long userId) throws NoAccessPermittedException {
        Team team = this.service.getTeamByUser(userId);

        if(!this.validator.validateTeam(userId, team.getId())) {
            throw new NoAccessPermittedException(userId);
        }

        return team;
    }

    @RequestMapping("/create")
    @PostMapping
    public Team create(@RequestBody Team team) {
        Objects.requireNonNull(team);

        return this.service.createTeam(team);
    }

    @RequestMapping("/update")
    @PutMapping
    public Team update(@RequestParam Long id, @RequestBody Team newTeam, @RequestParam Long userId) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(userId);
        Objects.requireNonNull(newTeam);
        return this.service.updateTeam(id, newTeam);
    }

    @RequestMapping("/delete")
    @DeleteMapping
    public boolean delete(@RequestParam Long id, @RequestParam Long userId) throws NoAccessPermittedException {
        Objects.requireNonNull(id);
        Objects.requireNonNull(userId);

        if(!this.validator.validateTeam(userId, id)) {
            throw new NoAccessPermittedException(userId);
        }

        return this.service.delete(id);
    }
}