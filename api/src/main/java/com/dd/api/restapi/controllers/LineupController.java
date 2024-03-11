package com.dd.api.restapi.controllers;

import com.dd.api.restapi.models.DefensivePlayer;
import com.dd.api.restapi.models.Lineup;
import com.dd.api.restapi.models.Pitcher;
import com.dd.api.restapi.requestmodels.CreateLineupRequestModel;
import com.dd.api.restapi.services.LineupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diamond-data/api/lineups")
public class LineupController {

    @Autowired
    private final LineupService service;

    @Autowired
    public LineupController(LineupService service) {
        this.service = service;
    }

    @GetMapping
    @RequestMapping("/get")
    public Lineup getLineupById(@RequestParam Long id) {
        return this.service.getLineupById(id);
    }

    @GetMapping
    @RequestMapping("/get-by-team")
    public List<Lineup> getByTeam(@RequestParam Long teamId) {
        return this.service.getByTeamId(teamId);
    }

    @GetMapping
    @RequestMapping("/get-all")
    public List<Lineup> getAll() {
        return this.service.getAll();
    }

    @PutMapping
    @RequestMapping("/update-first-base")
    public Lineup updateFirstBase(@RequestParam Long id, @RequestParam Long playerId) {
        return this.service.updateFirstBase(id, playerId);
    }

    @PutMapping
    @RequestMapping("/update-second-base")
    public Lineup updateSecondBase(@RequestParam Long id, @RequestParam Long playerId) {
        return this.service.updateSecondBase(id, playerId);
    }

    @PutMapping
    @RequestMapping("/update-third-base")
    public Lineup updateThirdBase(@RequestParam Long id, @RequestParam Long playerId) {
        return this.service.updateThirdBase(id, playerId);
    }

    @PutMapping
    @RequestMapping("/update-short-stop")
    public Lineup updateShortStop(@RequestParam Long id, @RequestParam Long playerId) {
        return this.service.updateShortStop(id, playerId);
    }

    @PutMapping
    @RequestMapping("/update-left-field")
    public Lineup updateLeftField(@RequestParam Long id, @RequestParam Long playerId) {
        return this.service.updateLeftField(id, playerId);
    }

    @PutMapping
    @RequestMapping("/update-right-field")
    public Lineup updateRightField(@RequestParam Long id, @RequestParam Long playerId) {
        return this.service.updateRightField(id, playerId);
    }

    @PutMapping
    @RequestMapping("/update-center-field")
    public Lineup updateCenterField(@RequestParam Long id, @RequestParam Long playerId) {
        return this.service.updateCenterField(id, playerId);
    }

    @PutMapping
    @RequestMapping("/update-catcher")
    public Lineup updateCatcher(@RequestParam Long id, @RequestParam Long playerId) {
        return this.service.updateCatcher(id, playerId);
    }

    @PutMapping
    @RequestMapping("/update-starting-pitcher")
    public Lineup updateStartingPitcher(@RequestParam Long id, @RequestParam Long playerId) {
        return this.service.updatePitcher(id, playerId);
    }

    @DeleteMapping
    @RequestMapping("/delete")
    public boolean deleteLineup(@RequestParam Long id) {
        return this.service.deleteLineup(id);
    }

    @PostMapping
    @RequestMapping("/create")
    public Lineup createLineup(@RequestBody CreateLineupRequestModel model) {
        return this.service.createLineup(model);
    }
}