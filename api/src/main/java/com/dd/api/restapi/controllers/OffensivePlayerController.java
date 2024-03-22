package com.dd.api.restapi.controllers;

import com.dd.api.auth.validators.Validator;
import com.dd.api.restapi.models.OffensivePlayer;
import com.dd.api.restapi.services.OffensivePlayerService;
import com.dd.api.util.exceptions.NoAccessPermittedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/diamond-data/api/offensive-players")
public class OffensivePlayerController {

    private final OffensivePlayerService service;
    private final Validator validator;

    @Autowired
    public OffensivePlayerController(OffensivePlayerService service, Validator validator) {
        this.service = service;
        this.validator = validator;
    }

    @RequestMapping("/get")
    @GetMapping
    public OffensivePlayer get(@RequestParam Long id, @RequestParam Long userId) throws NoAccessPermittedException {
        Objects.requireNonNull(id);
        Objects.requireNonNull(userId);
        if (!this.validator.validateOffensivePlayer(userId, id)) {
            throw new NoAccessPermittedException(userId);
        }
        return this.service.getOffensivePlayer(id);
    }

    @RequestMapping("/get-by-team")
    @GetMapping
    public List<OffensivePlayer> getByTeam(@RequestParam Long teamId, @RequestParam Long userId) throws Exception {
        Objects.requireNonNull(teamId);
        Objects.requireNonNull(userId);

        if (!this.validator.validateTeam(userId, teamId)) {
            throw new NoAccessPermittedException(userId);
        }

        return this.service.getByTeam(teamId);
    }

    @RequestMapping("/update")
    @PutMapping
    public OffensivePlayer update(@RequestParam Long id, @RequestBody OffensivePlayer player, @RequestParam Long userId) throws NoAccessPermittedException {
        Objects.requireNonNull(id);
        Objects.requireNonNull(player);
        Objects.requireNonNull(userId);

        if(!this.validator.validateOffensivePlayer(userId, id)) {
            throw new NoAccessPermittedException(userId);
        }

        return this.service.update(id, player);
    }

    @RequestMapping("/delete")
    @DeleteMapping
    public boolean delete(@RequestParam Long id, @RequestParam Long userId) throws NoAccessPermittedException {
        Objects.requireNonNull(userId);
        Objects.requireNonNull(id);
        if (!this.validator.validateOffensivePlayer(userId, id)) {
            throw new NoAccessPermittedException(userId);
        }
        return this.service.delete(id);
    }

    @RequestMapping("/create")
    @PostMapping
    public OffensivePlayer create(@RequestBody OffensivePlayer player, @RequestParam Long userId) throws NoAccessPermittedException {
        Objects.requireNonNull(player);
        if (!this.validator.validateTeam(userId, player.getTeam().getId())) {
            throw new NoAccessPermittedException(userId);
        }
        return this.service.createPlayer(player);
    }
}