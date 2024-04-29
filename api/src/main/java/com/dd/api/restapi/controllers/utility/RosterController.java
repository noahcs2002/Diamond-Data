package com.dd.api.restapi.controllers.utility;

import com.dd.api.auth.validators.Validator;
import com.dd.api.restapi.requestmodels.DeleteRequestModel;
import com.dd.api.restapi.models.Pitcher;
import com.dd.api.restapi.models.Player;
import com.dd.api.restapi.requestmodels.BulkPlayerChangeRequestModel;
import com.dd.api.restapi.requestmodels.EditModel;
import com.dd.api.restapi.requestmodels.TruncatedPlayerModel;
import com.dd.api.restapi.services.RosterService;
import com.dd.api.util.exceptions.NoAccessPermittedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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

    @PutMapping
    @RequestMapping("/perform-deletions")
    public boolean bulkDeletions(@RequestParam Long userId, @RequestParam Long teamId, @RequestBody DeleteRequestModel deleteRequestModel) throws NoAccessPermittedException {
        Objects.requireNonNull(userId);
        Objects.requireNonNull(teamId);
        Objects.requireNonNull(deleteRequestModel);

        if (!validator.validateTeam(userId, teamId)) {
            throw new NoAccessPermittedException(userId);
        }

        return  this.rosterService.bulkDeletePlayers(deleteRequestModel.getPlayersToDelete()) &&
                this.rosterService.bulkDeletePitchers(deleteRequestModel.getPitchersToDelete());
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

    @PutMapping
    @RequestMapping("bulk-update")
    public List<TruncatedPlayerModel> bulkUpdate(@RequestParam Long userId,
                                                     @RequestParam Long teamId,
                                                     @RequestBody List<TruncatedPlayerModel> models) throws NoAccessPermittedException {
        if(!this.validator.validateTeam(userId, teamId)) {
            throw new NoAccessPermittedException(userId);
        }

        
        
        
        return this.rosterService.performBulkPositionUpdate(models);
    }

    @PutMapping
    @RequestMapping("/bulk-player-changes")
    public EditModel bulkPlayerChange(@RequestParam Long userId,
                                                         @RequestParam Long teamId,
                                                         @RequestBody EditModel model)
            throws NoAccessPermittedException {
        if (!this.validator.validateTeam(userId, teamId)) {
            throw new NoAccessPermittedException(userId);
        }

        return this.rosterService.bulkUpdatePlayers(model, teamId);
    }

    @DeleteMapping
    @RequestMapping("/bulk-delete-pitchers")
    public boolean bulkDeletePitchers(@RequestParam Long userId, @RequestParam Long teamId, @RequestBody List<Pitcher> pitchers) throws NoAccessPermittedException {
        if(!this.validator.validateTeam(userId, teamId)) {
            throw new NoAccessPermittedException(userId);
        }

        return this.rosterService.bulkDeletePitchers(pitchers);
    }

    @DeleteMapping
    @RequestMapping("/bulk-delete-players")
    public boolean bulkDeletePlayers(@RequestParam Long userId, @RequestParam  Long teamId, @RequestBody List<Player> playersToDelete) throws NoAccessPermittedException {

        if(!this.validator.validateTeam(userId, teamId)) {
            throw new NoAccessPermittedException(userId);
        }

        return this.rosterService.bulkDeletePlayers(playersToDelete);
    }
}