package com.dd.api.restapi.controllers.utility;

import com.dd.api.auth.validators.Validator;
import com.dd.api.restapi.models.Pitcher;
import com.dd.api.restapi.models.Player;
import com.dd.api.restapi.services.RosterService;
import com.dd.api.util.exceptions.NoAccessPermittedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diamond-data/api/rosters")
public class RosterController {

    @Autowired
    private final RosterService rosterService;

    @Autowired
    private final Validator validator;

    @Autowired
    public RosterController(RosterService rosterService, Validator validator){
        this.rosterService = rosterService;
        this.validator = validator;
    }

    @GetMapping
    @RequestMapping("/get")
    public List<Player> getAllPlayersForRoster(@RequestParam Long userId, @RequestParam Long teamId) throws NoAccessPermittedException {

        if (!this.validator.validateTeam(userId, teamId)) {
            throw new NoAccessPermittedException(userId);
        }

        return this.rosterService.getAllPlayers(teamId);
    }

    @PutMapping
    @RequestMapping("/update-assignment")
    public Player updatePlayerAssignment(@RequestParam Long playerId, @RequestParam Long userId, @RequestParam String newAssignment) throws NoAccessPermittedException {

        if(!this.validator.validatePlayer(userId, playerId)) {
            throw new NoAccessPermittedException(userId);
        }

        return this.rosterService.updateAssignment(playerId, newAssignment);
    }

    @PutMapping
    @RequestMapping("/update-pitcher-assignment")
    public Pitcher updatePitcherAssignment(@RequestParam Long pitcherId, @RequestParam Long userId, @RequestParam String newAssignment) throws NoAccessPermittedException {
        if(!this.validator.validatePitcher(userId, pitcherId)) {
            throw new NoAccessPermittedException(userId);
        }

        return this.rosterService.updatePitcherAssignment(pitcherId, newAssignment);
    }
}