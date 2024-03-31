package com.dd.api.restapi.controllers;

import com.dd.api.restapi.models.Player;
import com.dd.api.restapi.models.Team;
import com.dd.api.restapi.requestmodels.PlayerUpdateRequestModel;
import com.dd.api.restapi.services.DefensivePlayerService;
import com.dd.api.restapi.services.OffensivePlayerService;
import com.dd.api.restapi.services.PlayerService;
import com.dd.api.restapi.services.TeamService;
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

    @Autowired
    private final TeamService teamService;

    public PlayerController(PlayerService playerService, OffensivePlayerService offensivePlayerService, DefensivePlayerService defensivePlayerService, TeamService teamService) {
        this.playerService = playerService;
        this.offensivePlayerService = offensivePlayerService;
        this.defensivePlayerService = defensivePlayerService;
        this.teamService = teamService;
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

    @RequestMapping("/get-by-team")
    @GetMapping
    public List<Player> getByTeam(@RequestParam Long teamId) {
        Objects.requireNonNull(teamId);
        return this.playerService.getByTeamId(teamId);
    }

    @RequestMapping("/create")
    @PostMapping
    public Player create(@RequestBody Player player, @RequestParam Long teamId) {
        Objects.requireNonNull(player);
        Objects.requireNonNull(teamId);
        Team team = this.teamService.getTeamById(teamId);
        return this.playerService.createPlayer(player, team);
    }

    @RequestMapping("/delete")
    @DeleteMapping
    public boolean deletePlayer(@RequestParam Long id) {
        Objects.requireNonNull(id);
        return this.playerService.deletePlayer(id);
    }

    @RequestMapping("/update")
    @PutMapping
    public Player updatePlayer(@RequestParam Long id, @RequestParam Long teamId, @RequestBody PlayerUpdateRequestModel model) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(model);
        Objects.requireNonNull(teamId);
        return this.playerService.update(id, model, teamId);
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