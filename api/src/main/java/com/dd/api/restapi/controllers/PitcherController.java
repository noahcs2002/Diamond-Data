package com.dd.api.restapi.controllers;

import com.dd.api.auth.validators.Validator;
import com.dd.api.restapi.models.Pitcher;
import com.dd.api.restapi.services.PitcherService;
import com.dd.api.util.exceptions.NoAccessPermittedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/diamond-data/api/pitchers")
public class PitcherController {

    private final PitcherService service;
    private final Validator validator;

    @Autowired
    public PitcherController(PitcherService service, Validator validator) {
        this.service = service;
        this.validator = validator;
    }

    @RequestMapping("/get")
    @GetMapping
    public Pitcher get(@RequestParam Long id, @RequestParam Long userId) throws NoAccessPermittedException {
        Objects.requireNonNull(id);
        Objects.requireNonNull(userId);

        if (!this.validator.validatePitcher(userId, id)) {
            throw new NoAccessPermittedException(userId);
        }
        return this.service.getPitcherById(id);
    }

    @RequestMapping("/get-by-team")
    @GetMapping
    public List<Pitcher> getByTeam(@RequestParam Long teamId, @RequestParam Long userId) throws Exception {
        Objects.requireNonNull(teamId);
        Objects.requireNonNull(userId);

        if(!this.validator.validateTeam(userId, teamId)) {
            throw new NoAccessPermittedException(userId);
        }

        return this.service.getPitchersByTeam(teamId);
    }

    @RequestMapping("/create")
    @PostMapping
    public Pitcher create(@RequestBody Pitcher pitcher, @RequestParam Long userId) throws NoAccessPermittedException {
        Objects.requireNonNull(pitcher);
        Objects.requireNonNull(userId);

        if(!this.validator.validateTeam(userId, pitcher.getTeam().getId())) {
            throw new NoAccessPermittedException(userId);
        }

        return this.service.createPitcher(pitcher);
    }

    @RequestMapping("/update")
    @PutMapping
    public Pitcher update(@RequestParam Long id, @RequestBody Pitcher newModel, @RequestParam Long userId) throws NoAccessPermittedException {
        Objects.requireNonNull(id);
        Objects.requireNonNull(newModel);
        Objects.requireNonNull(userId);

        if(!this.validator.validatePitcher(userId, id)) {
            throw new NoAccessPermittedException(userId);
        }
        return this.service.updatePitcher(id, newModel);
    }

    @RequestMapping("/delete")
    @DeleteMapping
    public boolean delete(@RequestParam Long id, @RequestParam Long userId) throws NoAccessPermittedException {
        Objects.requireNonNull(id);
        Objects.requireNonNull(userId);

        if(!this.validator.validatePitcher(userId, id)) {
            throw new NoAccessPermittedException(userId);
        }
        return this.service.deletePitcher(id);
    }

    @RequestMapping("/ai")
    @GetMapping
    public List<Pitcher> getAiPitchers(@RequestParam Long userId, @RequestParam Long teamId) throws NoAccessPermittedException {
        Objects.requireNonNull(userId);
        Objects.requireNonNull(teamId);

        if (!this.validator.validateTeam(userId, teamId)) {
            throw new NoAccessPermittedException(userId);
        }

        return this.service.getAiPitchers(teamId);
    }
}