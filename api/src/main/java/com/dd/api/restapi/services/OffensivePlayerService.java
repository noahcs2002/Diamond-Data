package com.dd.api.restapi.services;

import com.dd.api.ai.agents.OffensivePlayerAgent;
import com.dd.api.ai.scoring.OffensivePlayerScoringStrategy;
import com.dd.api.restapi.calculators.OffensivePlayerStatisticCalculator;
import com.dd.api.restapi.models.OffensivePlayer;
import com.dd.api.restapi.repositories.OffensivePlayerRepository;
import com.dd.api.restapi.requestmodels.AtBatResultModel;
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
        OffensivePlayer player = OffensivePlayerStatisticCalculator.updateOffensivePlayerStatistics(newPlayer);
        return this.repository.save(player);
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

    @Transactional
    public boolean recordAtBat(OffensivePlayer offensivePlayer, AtBatResultModel atBatResult) {
        switch (atBatResult.toString().toUpperCase()) {
            case "SO" -> {
                offensivePlayer.setStrikeouts(offensivePlayer.getStrikeouts() + 1);
            }
            case "BB" -> {
                offensivePlayer.setWalks(offensivePlayer.getWalks() + 1);
            }

            case "1B" -> {
                offensivePlayer.setSingles(offensivePlayer.getSingles() + 1);
            }

            case "2B" -> {
                offensivePlayer.setDoubles(offensivePlayer.getDoubles() + 1);
            }

            case "3B" -> {
                offensivePlayer.setTriples(offensivePlayer.getTriples() + 1);
            }

            case "HBP" -> {
                offensivePlayer.setHitByPitch(offensivePlayer.getHitByPitch() + 1);
            }

            case "HR" -> {
                offensivePlayer.setHomeRuns(offensivePlayer.getHomeRuns() + 1);
            }

            case "SF" -> {
                offensivePlayer.setSacrificeFly(offensivePlayer.getSacrificeFly() + 1);
            }

            case "BU" -> {
                offensivePlayer.setSacrificeBunt(offensivePlayer.getSacrificeBunt() + 1);
            }
            default -> { System.out.println("Invalid input"); return false; }
        }

        offensivePlayer.setAtBats(offensivePlayer.getAtBats() + 1);
        this.repository.save(offensivePlayer);
        return true;
    }
}