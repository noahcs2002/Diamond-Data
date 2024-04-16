package com.dd.api.restapi.services;

import com.dd.api.restapi.models.Pitcher;
import com.dd.api.restapi.models.Player;
import com.dd.api.restapi.repositories.PitcherRepository;
import com.dd.api.restapi.repositories.PlayerRepository;
import com.dd.api.restapi.requestmodels.BulkPositionUpdateRequestModel;
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
    public RosterService(PlayerRepository repository, PitcherRepository pitcherRepository) {
        this.repository = repository;
        this.pitcherRepository = pitcherRepository;
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
}
