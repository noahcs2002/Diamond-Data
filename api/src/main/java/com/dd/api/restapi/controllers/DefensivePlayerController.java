package com.dd.api.restapi.controllers;

import com.dd.api.auth.validators.Validator;
import com.dd.api.restapi.models.DefensivePlayer;
import com.dd.api.restapi.services.DefensivePlayerService;
import com.dd.api.restapi.services.TeamService;
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

    @Autowired
    public DefensivePlayerController(DefensivePlayerService service, TeamService teamService, Validator validator) {
        this.service = service;
        this.teamService = teamService;
        this.validator = validator;
    }

    @RequestMapping("/get")
    @GetMapping
    public DefensivePlayer get(@RequestParam Long id, @RequestParam Long userId) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(userId);
        return this.service.getDefensivePlayer(id);
    }

    @RequestMapping("/get-by-team")
    @GetMapping
    public List<DefensivePlayer> getByTeam(@RequestParam Long teamId, @RequestParam Long userId) {
        Objects.requireNonNull(userId);
        Objects.requireNonNull(teamId);
        return this.service.getAllPlayersByTeam(teamId);
    }

    @RequestMapping("/create")
    public DefensivePlayer create(@RequestBody DefensivePlayer player, @RequestParam Long userId) {
        Objects.requireNonNull(player);
        Objects.requireNonNull(userId);
        return this.service.createDefensivePlayer(player);
    }

    @RequestMapping("/update")
    public DefensivePlayer update(@RequestParam Long id, @RequestBody DefensivePlayer player, @RequestParam Long userId) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(userId);
        Objects.requireNonNull(player);
        return this.service.updateDefensivePlayer(id, player);
    }

    @RequestMapping("/delete")
    public boolean delete(@RequestParam Long id, @RequestParam Long userId) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(userId);
        return this.service.deletePlayer(id);
    }
}