package com.dd.api.restapi.controllers;

import com.dd.api.auth.validators.Validator;
import com.dd.api.restapi.models.DefensivePlayer;
import com.dd.api.restapi.services.DefensivePlayerService;
import com.dd.api.restapi.services.OffensivePlayerService;
import com.dd.api.restapi.services.TeamService;
import com.dd.api.util.exceptions.NoAccessPermittedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/diamond-data/api/defensive-players")
public class DefensivePlayerController {

    @Autowired
    private final DefensivePlayerService service;

    @Autowired
    private final Validator validator;

    @Autowired
    private final TeamService teamService;

    private final String exceptionMessage = "Cannot access the specified resource with the specified user id: ";

    @Autowired
    public DefensivePlayerController(DefensivePlayerService service, TeamService teamService, Validator validator) {
        this.service = service;
        this.teamService = teamService;
        this.validator = validator;
    }

    @RequestMapping("/get")
    @GetMapping
    public DefensivePlayer get(@RequestParam Long id, @RequestParam Long userId) throws Exception {
        Objects.requireNonNull(id);
        Objects.requireNonNull(userId);

        if (!this.validator.validateDefensivePlayer(userId, id)) {
            throw new IllegalAccessException(exceptionMessage + userId);
        }

        return this.service.getDefensivePlayer(id);
    }

    @RequestMapping("/get-by-team")
    @GetMapping
    public List<DefensivePlayer> getByTeam(@RequestParam Long teamId, @RequestParam Long userId) throws IllegalAccessException{
        Objects.requireNonNull(userId);
        Objects.requireNonNull(teamId);

        if(!this.validator.validateTeam(userId, teamId)) {
            throw new IllegalAccessException(exceptionMessage + userId);
        }

        return this.service.getAllPlayersByTeam(teamId);
    }

    @RequestMapping("/create")
    @PostMapping
    public DefensivePlayer create(@RequestBody DefensivePlayer player, @RequestParam Long userId) throws Exception {
        Objects.requireNonNull(player);
        Objects.requireNonNull(userId);

        if (!this.validator.validateTeam(userId, player.getTeam().getId())) {
            throw new IllegalAccessException(exceptionMessage + player.getTeam().getId());
        }

        return this.service.createDefensivePlayer(player);
    }

    @RequestMapping("/update")
    @PutMapping
    public DefensivePlayer update(@RequestParam Long id, @RequestBody DefensivePlayer player, @RequestParam Long userId) throws Exception {
        Objects.requireNonNull(id); Objects.requireNonNull(userId); Objects.requireNonNull(player);

        if(!this.validator.validateDefensivePlayer(userId, id)) {
            throw new IllegalAccessException(exceptionMessage + userId);
        }

        return this.service.updateDefensivePlayer(id, player);
    }

    @RequestMapping("/delete")
    @DeleteMapping
    public boolean delete(@RequestParam Long id, @RequestParam Long userId) throws Exception {
        Objects.requireNonNull(id);
        Objects.requireNonNull(userId);
        if (!this.validator.validateDefensivePlayer(userId, id)) {
            throw new IllegalAccessException(exceptionMessage + userId);
        }
        return this.service.deletePlayer(id);
    }

    @RequestMapping
    @GetMapping
    public List<DefensivePlayer> getAiDefensivePlayers(@RequestParam Long userId, @RequestParam Long teamId) throws NoAccessPermittedException {
        Objects.requireNonNull(userId);
        Objects.requireNonNull(teamId);

        if (!this.validator.validateTeam(userId, teamId)) {
            throw new NoAccessPermittedException(userId);
        }

        return this.service.getAiDefensivePlayers(teamId);
    }

}