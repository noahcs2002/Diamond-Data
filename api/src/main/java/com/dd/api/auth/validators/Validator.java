package com.dd.api.auth.validators;

import com.dd.api.restapi.models.*;
import com.dd.api.restapi.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Used to verify that a user has permissions to be accessing a resource they are requesting.
 */
@Service
public class Validator {

    @Autowired
    private final TeamService teamService;

    @Autowired
    private final OffensivePlayerService offensivePlayerService;

    @Autowired
    private final DefensivePlayerService defensivePlayerService;

    @Autowired
    private final GameService gameService;

    @Autowired
    private final PitcherService pitcherService;

    @Autowired
    private final PlayerService playerService;

    @Autowired
    private final NoteService noteService;

    public Validator(TeamService teamService,
                     OffensivePlayerService offensivePlayerService,
                     DefensivePlayerService defensivePlayerService,
                     GameService gameService,
                     PitcherService pitcherService,
                     PlayerService playerService,
                     NoteService noteService) {
        this.teamService = teamService;
        this.offensivePlayerService = offensivePlayerService;
        this.defensivePlayerService = defensivePlayerService;
        this.gameService = gameService;
        this.pitcherService = pitcherService;
        this.playerService = playerService;
        this.noteService = noteService;
    }

    /**
     * Validate a user to make sure they can access the team they are requesting
     * @param userId The id of the user attempting to access the resource
     * @param teamId The id team they are attempting to access
     * @return True if they are validated, else false
     * @author Noah Sternberg
     */
    public boolean validateTeam(Long userId, Long teamId) {
        Objects.requireNonNull(userId);
        Objects.requireNonNull(teamId);

        Team res = this.teamService.getTeamById(teamId);

        return res != null && res.getUser().getId().equals(userId);
    }


    /**
     * Validate a user to make sure they can access the team they are requesting
     * @param userId The id of the user attempting to access the resource
     * @param playerId The id of the defensivePlayer they are attempting to access
     * @return True if they are validated, else false
     * @author Noah Sternberg
     */
    public boolean validateDefensivePlayer(Long userId, Long playerId) {
        Objects.requireNonNull(userId);
        Objects.requireNonNull(playerId);

        DefensivePlayer res = this.defensivePlayerService.getDefensivePlayer(playerId);

        return res != null && validateTeam(userId, res.getTeam().getId());
    }

    /**
     * Validate a user to make sure they can access the team they are requesting
     * @param userId The id of the user attempting to access the resource
     * @param gameId The id of the game they are attempting to access
     * @return True if they are validated, else false
     * @author Noah Sternberg
     */
    public boolean validateGame(Long userId, Long gameId) {
        Objects.requireNonNull(userId);
        Objects.requireNonNull(gameId);

        Game res = this.gameService.getGameById(gameId);

        return res != null && validateTeam(userId, res.getTeam().getId());
    }

    /**
     * Validate a user to make sure they can access the team they are requesting
     * @param userId The id of the user attempting to access the resource
     * @param offensivePlayerId The id of the offensivePlayer they are attempting to access
     * @return True if they are validated, else false
     * @author Noah Sternberg
     */
    public boolean validateOffensivePlayer(Long userId, Long offensivePlayerId) {
        Objects.requireNonNull(userId);
        Objects.requireNonNull(offensivePlayerId);

        OffensivePlayer res = this.offensivePlayerService.getOffensivePlayer(offensivePlayerId);

        return res != null && validateTeam(userId, res.getTeam().getId());
    }

    /**
     * Validate a user to make sure they can access the team they are requesting
     * @param userId The id of the user attempting to access the resource
     * @param pitcherId The id of the pitcher they are attempting to access
     * @return True if they are validated, else false
     * @author Noah Sternberg
     */
    public boolean validatePitcher(Long userId, Long pitcherId) {
        Objects.requireNonNull(userId);
        Objects.requireNonNull(pitcherId);

        Pitcher res = this.pitcherService.getPitcherById(pitcherId);

        return res != null && validateTeam(userId, res.getTeam().getId());
    }

    /**
     * Validate a user to make sure they can access the player they are requesting
     * @param userId The id of the user attempting to access the resource
     * @param playerId The id player they are attempting to access
     * @return True if they are validated, else false
     * @author Noah Sternberg
     */
    public boolean validatePlayer(Long userId, Long playerId) {
        Objects.requireNonNull(userId);
        Objects.requireNonNull(playerId);

        Player res = this.playerService.getPlayerById(playerId);

        return res != null
                && validateTeam(userId, res.getOffensivePlayer().getTeam().getId())
                && validateTeam(userId, res.getDefensivePlayer().getTeam().getId());
    }

    public boolean validateNote(Long userId, Long noteId) {
        Objects.requireNonNull(userId);
        Objects.requireNonNull(noteId);

        Note note = this.noteService.getNote(noteId);

        return note != null && validateTeam(userId, note.getTeam().getId());
    }
}