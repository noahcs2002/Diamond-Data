package com.dd.api.restapi.controllers;

import com.dd.api.restapi.models.Player;
import com.dd.api.restapi.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diamond-data/api/players")
public class PlayerController {

    private final PlayerService service;

    @Autowired
    public PlayerController(PlayerService service) {
        this.service = service;
    }

    @RequestMapping("/get")
    @GetMapping
    public Player get(@RequestParam Long id) {
        return this.service.getPlayerById(id);
    }

    @RequestMapping("/get-all")
    @GetMapping
    public List<Player> getAll() {
        return this.service.getAll();
    }

    @RequestMapping
    @GetMapping("/get-by-team")
    public List<Player> getByTeam(@RequestParam Long teamId) {
        return this.service.getByTeamId(teamId);
    }

    @RequestMapping("/create")
    @PostMapping
    public Player create(@RequestBody Player player) {
        return this.service.createPlayer(player);
    }

    @RequestMapping("/delete")
    @DeleteMapping
    public boolean deletePlayer(@RequestParam Long id) {
        return this.service.deletePlayer(id);
    }
}