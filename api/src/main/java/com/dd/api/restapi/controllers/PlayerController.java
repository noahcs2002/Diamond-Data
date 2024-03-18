package com.dd.api.restapi.controllers;

import com.dd.api.restapi.models.Player;
import com.dd.api.restapi.requestmodels.PlayerManipulationRequestModel;
import com.dd.api.restapi.requestmodels.PlayerUpdateRequestModel;
import com.dd.api.restapi.services.DefensivePlayerService;
import com.dd.api.restapi.services.OffensivePlayerService;
import com.dd.api.restapi.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/diamond-data/api/players")
public class PlayerController {

    @Autowired
    private final PlayerService playerService;

    @Autowired
    private final OffensivePlayerService offensivePlayerService;

    @Autowired
    private final DefensivePlayerService defensivePlayerService;

    public PlayerController(PlayerService playerService, OffensivePlayerService offensivePlayerService, DefensivePlayerService defensivePlayerService) {
        this.playerService = playerService;
        this.offensivePlayerService = offensivePlayerService;
        this.defensivePlayerService = defensivePlayerService;
    }

    @RequestMapping("/get")
    @GetMapping
    public Player get(@RequestParam Long id) {
        Objects.requireNonNull(id);
        return this.playerService.getPlayerById(id);
    }

    @RequestMapping("/get-all")
    @GetMapping
    public List<Player> getAll() {
        return this.playerService.getAll();
    }

    @RequestMapping
    @GetMapping("/get-by-team")
    public List<Player> getByTeam(@RequestParam Long teamId) {
        Objects.requireNonNull(teamId);
        return this.playerService.getByTeamId(teamId);
    }

    @RequestMapping("/create")
    @PostMapping
    public Player create(@RequestBody PlayerManipulationRequestModel requestModel) {
        Objects.requireNonNull(requestModel);
        return this.playerService.createPlayer(requestModel);
    }

    @RequestMapping("/delete")
    @DeleteMapping
    public boolean deletePlayer(@RequestParam Long id) {
        Objects.requireNonNull(id);
        return this.playerService.deletePlayer(id);
    }

    @RequestMapping("/update")
    @PostMapping
    public Player updatePlayer(@RequestParam Long id, @RequestBody PlayerUpdateRequestModel model) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(model);
        return this.playerService.update(id, model);
    }

    @RequestMapping("/change-first-name")
    @PutMapping
    public Player changeFirstName(@RequestParam Long id, @RequestParam String newFirstName) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(newFirstName);
        return this.playerService.changeFirstName(id, newFirstName);
    }

    @RequestMapping("/change-last-name")
    @PutMapping
    public Player changeLastName(@RequestParam Long id, @RequestParam String newLastName) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(newLastName);
        return this.playerService.changeLastName(id, newLastName);
    }
}