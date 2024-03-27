package com.dd.api.restapi.services;

import com.dd.api.ai.agents.DefensivePlayerAgent;
import com.dd.api.ai.scoring.DefensivePlayerScoringStrategy;
import com.dd.api.restapi.models.DefensivePlayer;
import com.dd.api.restapi.repositories.DefensivePlayerRepository;
import com.dd.api.util.TruncatedSystemTimeProvider;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefensivePlayerService {

    private final DefensivePlayerRepository repository;

    @Autowired
    public DefensivePlayerService(DefensivePlayerRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public DefensivePlayer createDefensivePlayer(DefensivePlayer player) {
        return this.repository.save(player);
    }

    @Transactional
    public DefensivePlayer getDefensivePlayer(Long id) {
        return this.repository.findById(id)
                .filter(p -> p.getGhostedDate() == 0)
                .orElse(null);
    }

    @Transactional
    public List<DefensivePlayer> getAllPlayersByTeam(Long teamId) {
        return this.repository.findAll()
                .stream()
                .filter(p -> p.getGhostedDate() == 0)
                .filter(p -> p.getTeam().getId().equals(teamId))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<DefensivePlayer> getAllPlayers() {
        return this.repository.findAll()
                .stream()
                .filter(p -> p.getGhostedDate() == 0)
                .collect(Collectors.toList());
    }

    @Transactional
    public DefensivePlayer updateDefensivePlayer(Long id, DefensivePlayer player) {
        player.setId(id);
        return this.repository.save(player);
    }

    @Transactional
    public boolean deletePlayer(Long id) {
        this.repository.findById(id).ifPresent(p -> {
            p.setGhostedDate(new TruncatedSystemTimeProvider().provideTime());
            this.repository.save(p);
        });

        return true;
    }

    @Transactional
    public DefensivePlayer getNonTransientInstance(DefensivePlayer transientInstance) {
        return this.repository.findAll()
                .stream()
                .filter(p -> p.equals(transientInstance))
                .filter(p -> p.getGhostedDate() == 0)
                .findFirst()
                .orElse(null);
    }

    @Transactional
    public List<DefensivePlayer> getAiDefensivePlayers(Long teamId) {

        List<DefensivePlayer> players = this.repository.findAll()
                .stream()
                .filter(p -> p.getGhostedDate() == 0)
                .filter(p -> p.getTeam().getId().equals(teamId))
                .toList();

        return new DefensivePlayerAgent(players, new DefensivePlayerScoringStrategy()).getSortedAndWeightedDefensivePlayers();
    }
}