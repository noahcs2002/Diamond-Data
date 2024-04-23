package com.dd.api.restapi.services;

import com.dd.api.restapi.models.Pitcher;
import com.dd.api.restapi.models.Player;
import com.dd.api.restapi.models.Team;
import com.dd.api.restapi.repositories.PitcherRepository;
import com.dd.api.restapi.repositories.PlayerRepository;
import com.dd.api.restapi.requestmodels.BulkPlayerChangeRequestModel;
import com.dd.api.restapi.requestmodels.TruncatedPlayerModel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public BulkPlayerChangeRequestModel bulkUpdatePlayers(BulkPlayerChangeRequestModel model, Long teamId) {

        model.getPlayers()
                .forEach(p -> {
                    Player instance = this.repository.findById(p.getId()).orElse(null);
                    if (instance == null) {
                        Team team = this.teamService.getTeamById(teamId);
                        this.playerService.createPlayer(p, team);
                    }
                    else {
                        this.repository.save(instance);
                        this.playerService.changeFirstName(p.getId(), p.getFirstName());
                        this.playerService.changeLastName(p.getId(), p.getLastName());
                    }
                });

        model.getPitchers()
                .forEach(p -> {
                    if (p.getId() == null) {
                        this.pitcherService.createPitcher(p, teamId);
                    }
                    else {
                        Pitcher instance = this.pitcherRepository.findById(p.getId()).orElse(null);
                        if (instance != null) {
                            this.pitcherRepository.save(instance);
                            this.pitcherService.updatePitcherName(p.getId(), p.getFirstName(), p.getLastName());
                        }
                    }
                });

        return model;
    }

    public boolean bulkDeletePlayers(List<Player> playersToDelete) {

        playersToDelete.forEach(p -> {
            try {
                this.playerService.deletePlayer(p.getId());
            }
            catch(Exception ex) {
                System.out.println(ex.getMessage());
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
                System.out.println(ex.getMessage());
            }
        });


        return true;

    }
}
