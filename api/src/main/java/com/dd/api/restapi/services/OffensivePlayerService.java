package com.dd.api.restapi.services;

import com.dd.api.ai.agents.OffensivePlayerAgent;
import com.dd.api.ai.scoring.OffensivePlayerScoringStrategy;
import com.dd.api.restapi.models.OffensivePlayer;
import com.dd.api.restapi.repositories.OffensivePlayerRepository;
import com.dd.api.util.TruncatedSystemTimeProvider;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OffensivePlayerService {

    private final OffensivePlayerRepository repository;

    @Autowired
    public OffensivePlayerService(OffensivePlayerRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public OffensivePlayer createPlayer(OffensivePlayer player) {
        return this.repository.save(player);
    }

    @Transactional
    public OffensivePlayer getOffensivePlayer(Long id) {
        return this.repository.findById(id)
                .filter(_p -> _p.getGhostedDate() == 0)
                .orElse(null);
    }

    @Transactional
    public List<OffensivePlayer> getAll() {
        return this.repository.findAll()
                .stream()
                .filter(_p -> _p.getGhostedDate() == 0)
                .toList();
    }

    @Transactional
    public List<OffensivePlayer> getByTeam(Long teamId) {
        return this.repository.findAll()
                .stream()
                .filter(_p -> _p.getGhostedDate() == 0)
                .filter(_p -> _p.getTeam().getId().equals(teamId))
                .collect(Collectors.toList());
    }

    @Transactional
    public OffensivePlayer update(Long id, OffensivePlayer newPlayer) {
        newPlayer.setId(id);
        return this.repository.save(newPlayer);
    }

    @Transactional
    public boolean delete(Long id) {
        this.repository.findById(id).ifPresent(_p -> {
            _p.setGhostedDate(new TruncatedSystemTimeProvider().provideTime());
            this.repository.save(_p);
        });
        return true;
    }

    @Transactional
    public List<OffensivePlayer> getAiOffensivePlayers(Long teamId) {
        List<OffensivePlayer> offensivePlayers = this.repository.findAll()
                .stream()
                .filter(p -> p.getTeam().getId().equals(teamId))
                .filter(p -> p.getGhostedDate() == 0)
                .toList();

        return new OffensivePlayerAgent(offensivePlayers, new OffensivePlayerScoringStrategy()).getSortedAndWeightedOffensivePlayers();
    }
}