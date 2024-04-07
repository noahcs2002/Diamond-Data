package com.dd.api.restapi.services;

import com.dd.api.restapi.models.Player;
import com.dd.api.restapi.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RosterService {

    @Autowired
    private final PlayerRepository repository;

    @Autowired
    public RosterService(PlayerRepository repository) {
        this.repository = repository;
    }

    public List<Player> getAllPlayers(Long teamId) {
        return this.repository.findAll()
                .stream()
                .filter(p -> p.getOffensivePlayer().getTeam().getId().equals(teamId))
                .filter(p -> p.getDefensivePlayer().getTeam().getId().equals(teamId))
                .filter(p -> p.getGhostedDate() == 0)
                .toList();
    }
}
