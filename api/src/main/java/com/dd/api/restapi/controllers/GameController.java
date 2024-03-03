package com.dd.api.restapi.controllers;

import com.dd.api.restapi.models.Game;
import com.dd.api.restapi.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diamond-data/api/games")
public class GameController {

    @Autowired
    private final GameService service;

    @Autowired
    public GameController(GameService service) {
        this.service = service;
    }

    @GetMapping
    @RequestMapping("/get")
    public Game get(@RequestParam Long id) {
        return this.service.getGameById(id);
    }

    @GetMapping
    @RequestMapping("/get-all")
    public List<Game> getAllGames() {
        return this.service.getAll();
    }

    @GetMapping
    @RequestMapping("/get-by-team")
    public List<Game> getAllGamesByTeam(@RequestParam Long teamId) {
        return this.service.getGamesByTeam(teamId);
    }

    @PutMapping
    @RequestMapping("/update")
    public Game updateGame(@RequestParam Long id, @RequestBody Game game) {
        return this.service.updateGame(id, game);
    }

    @PostMapping
    @RequestMapping("/create")
    public Game createGame(@RequestBody Game game) {
        return this.service.createGame(game);
    }

    @DeleteMapping
    @RequestMapping("/delete")
    public boolean createGame(@RequestParam Long id) {
        return this.service.deleteGame(id);
    }
}