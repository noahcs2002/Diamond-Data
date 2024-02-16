package com.dd.api.restapi.controllers;

import com.dd.api.restapi.models.DefensivePlayer;
import com.dd.api.restapi.services.DefensivePlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diamond-data/api/defensive-players")
public class DefensivePlayerController {

    private final DefensivePlayerService service;

    @Autowired
    public DefensivePlayerController(DefensivePlayerService service) {
        this.service = service;
    }

    @RequestMapping("/get")
    @GetMapping
    public DefensivePlayer get(@RequestParam Long id) {
        return this.service.getDefensivePlayer(id);
    }

    @RequestMapping("/get-all")
    @GetMapping
    public List<DefensivePlayer> getAll() {
        return this.service.getAllPlayers();
    }

    @RequestMapping("/get-by-team")
    @GetMapping
    public List<DefensivePlayer> getByTeam(@RequestParam Long teamId) {
        return this.service.getAllPlayersByTeam(teamId);
    }

    @RequestMapping("/create")
    public DefensivePlayer create(@RequestBody DefensivePlayer player) {
        return this.service.createDefensivePlayer(player);
    }

    @RequestMapping("/update")
    public DefensivePlayer update(@RequestParam Long id, @RequestBody DefensivePlayer player) {
        return this.service.updateDefensivePlayer(id, player);
    }

    @RequestMapping("/delete")
    public boolean delete(@RequestParam Long id) {
        return this.service.deletePlayer(id);
    }
}