package com.dd.api.restapi.services;

import com.dd.api.restapi.models.OffensivePlayer;
import com.dd.api.restapi.models.Pitcher;
import com.dd.api.restapi.models.Player;
import com.dd.api.restapi.models.Team;
import com.dd.api.restapi.repositories.PitcherRepository;
import com.dd.api.restapi.repositories.PlayerRepository;
import com.dd.api.restapi.requestmodels.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.hibernate.internal.util.collections.ArrayHelper.forEach;

@Service
public class RosterService {

    @Autowired
    private final PlayerRepository repository;

    @Autowired
    private final PitcherRepository pitcherRepository;

    @Autowired
    private final PlayerService playerService;

    @Autowired
    private final TeamService teamService;

    @Autowired
    private final PitcherService pitcherService;

    @Autowired
    public RosterService(PlayerRepository repository, PitcherRepository pitcherRepository, PlayerService playerService, TeamService teamService, PitcherService pitcherService) {
        this.repository = repository;
        this.pitcherRepository = pitcherRepository;
        this.playerService = playerService;
        this.teamService = teamService;
        this.pitcherService = pitcherService;
    }

    @Transactional
    public List<Player> getAllPlayers(Long teamId) {
        return this.repository.findAll()
                .stream()
                .filter(p -> p.getOffensivePlayer().getTeam().getId().equals(teamId))
                .filter(p -> p.getDefensivePlayer().getTeam().getId().equals(teamId))
                .filter(p -> p.getGhostedDate() == 0)
                .toList();
    }

    @Transactional
    public Player updateAssignment(Long playerId, String newAssignment) {
        return this.repository.findAll()
                .stream()
                .filter(p -> p.getId().equals(playerId))
                .peek(p -> {
                    p.setAssignment(newAssignment);
                    this.repository.save(p);
                })
                .toList()
                .get(0);
    }

    public Pitcher updatePitcherAssignment(Long pitcherId, String newAssignment) {
        return this.pitcherRepository.findAll()
                .stream()
                .filter(p -> p.getId().equals(pitcherId))
                .peek(p -> {
                    p.setAssignment(newAssignment);
                    this.pitcherRepository.save(p);
                })
                .toList()
                .get(0);
    }

    public List<TruncatedPlayerModel> performBulkPositionUpdate(List<TruncatedPlayerModel> models) {
        models.forEach(p -> {
             if (p.getPosition().equalsIgnoreCase("pitcher")) {
                 this.updatePitcherAssignment(p.getId(), p.getAssignment());
             }
             else {
                 this.updateAssignment(p.getId(), p.getAssignment());
             }
        });

        return models;
    }

    public EditModel bulkUpdatePlayers(EditModel model, Long teamId) {

        List<Player> players = model.getPlayers();
        List<Pitcher> pitchers = model.getPitchers();
        List<Player> playersToDelete = model.getPlayersToDelete();
        List<Pitcher> pitchersToDelete = model.getPitchersToDelete();

        if(players != null && !players.isEmpty()) {
           players.forEach(p -> {
                Player instance = this.repository.findById(p.getId()).orElse(null);
                if (instance == null) {
                    Team team = this.teamService.getTeamById(teamId);
                    this.playerService.createPlayer(p, team);
                }
                else {
                    this.playerService.updatePositions(p.getId(), p.getDefensivePlayer().getPositions());
                    this.playerService.changeFirstName(p.getId(), p.getFirstName());
                    this.playerService.changeLastName(p.getId(), p.getLastName());
                }
            });
        }


        if(pitchers != null && !pitchers.isEmpty()) {
           pitchers.forEach(p -> {
                if (p.getId() == null) {
                    this.pitcherService.createPitcher(p, teamId);
                }
                else {
                    Pitcher instance = this.pitcherRepository.findById(p.getId()).orElse(null);
                    if (instance != null) {
                        this.pitcherRepository.save(p);
                        this.pitcherService.updatePitcherName(p.getId(), p.getFirstName(), p.getLastName());
                    }
                }
            });
        }


        if(pitchersToDelete != null && !pitchersToDelete.isEmpty()) {
            this.bulkDeletePitchers(model.getPitchersToDelete());
        }

        if(playersToDelete != null && !playersToDelete.isEmpty()) {
            this.bulkDeletePlayers(model.getPlayersToDelete());
        }
        return model;
    }

    public boolean bulkDeletePlayers(List<Player> playersToDelete) {

        playersToDelete.forEach(p -> {
            try {
                this.playerService.deletePlayer(p.getId());
            }
            catch(Exception ex) {
                
            }
        });

        return true;
    }

    public boolean bulkDeletePitchers(List<Pitcher> pitchers) {

        pitchers.forEach(p -> {
            try{
                this.pitcherService.deletePitcher(p.getId());
            }
            catch(Exception ex) {
                
            }
        });

        return true;
    }

    public boolean recordAtBat(Long playerId, AtBatResultModel atBatResult) {

        String result = atBatResult.toString();
        Player player = this.playerService.getPlayerById(playerId);
        return this.playerService.recordAtBat(player, atBatResult);
    }

    public boolean recordGamePitched(Long pitcherId, GamePitchedModel game) {
        return this.pitcherService.recordGamePitched(pitcherId, game);
    }
}
