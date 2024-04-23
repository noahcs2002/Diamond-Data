package com.dd.api.restapi.services;

import com.dd.api.ai.agents.PitcherAgent;
import com.dd.api.ai.scoring.PitcherScoringStrategy;
import com.dd.api.restapi.calculators.PitcherStatisticsCalculator;
import com.dd.api.restapi.models.Pitcher;
import com.dd.api.restapi.models.Team;
import com.dd.api.restapi.repositories.PitcherRepository;
import com.dd.api.util.TruncatedSystemTimeProvider;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PitcherService {

    @Autowired
    private final PitcherRepository pitcherRepository;

    @Autowired
    private final TeamService teamService;


    @Autowired
    public PitcherService(PitcherRepository pitcherRepository, TeamService teamService) {
        this.pitcherRepository = pitcherRepository;
        this.teamService = teamService;
    }

    @Transactional
    public Pitcher createPitcher(Pitcher pitcher, Long teamId) {
        Team team = this.teamService.getTeamById(teamId);
        pitcher.setTeam(team);
        return this.pitcherRepository.save(pitcher);
    }

    @Transactional
    public Pitcher getPitcherById(Long id) {
        return this.pitcherRepository.findById(id)
                .filter(p -> p.getGhostedDate() == 0)
                .orElse(null);
    }

    @Transactional
    public List<Pitcher> getAll() {
        return this.pitcherRepository.findAll()
                .stream()
                .filter(p -> p.getGhostedDate() == 0)
                .toList();

    }

    @Transactional
    public Pitcher updatePitcher(Long id, Pitcher newModel) {
        Pitcher pitcher = this.pitcherRepository.getReferenceById(id);
        newModel.setId(pitcher.getId());
        pitcher = PitcherStatisticsCalculator.updateStats(newModel);
        this.pitcherRepository.save(pitcher);
        return pitcher;
    }

    @Transactional
    public boolean deletePitcher(Long id) {
        this.pitcherRepository.findById(id)
                .ifPresent(p -> {
                    p.setGhostedDate(new TruncatedSystemTimeProvider().provideTime());
                    pitcherRepository.save(p);
                });
        return true;
    }

    @Transactional
    public List<Pitcher> getPitchersByTeam(Long teamId) {
        List<Pitcher> pitchers = this.pitcherRepository.findAll();
        return pitchers.stream()
                .filter(p -> p.getGhostedDate() == 0)
                .filter(p -> Objects.equals(p.getTeam().getId(), teamId))
                .toList();
    }

    @Transactional
    public Pitcher getNonTransientInstance(Pitcher transientInstance) {
        return this.pitcherRepository.findAll()
                .stream()
                .filter(p -> p.equals(transientInstance))
                .filter(p -> p.getGhostedDate() == 0)
                .findFirst()
                .orElse(null);
    }

    @Transactional
    public List<Pitcher> getAiPitchers(Long teamId) {
        List<Pitcher> pitchers = this.pitcherRepository.findAll()
                .stream()
                .filter(p -> p.getTeam().getId().equals(teamId))
                .filter(p -> p.getGhostedDate() == 0)
                .toList();

        return new PitcherAgent(pitchers, new PitcherScoringStrategy()).getSortedAndWeightedPitchers();
    }

    @Transactional
    public Pitcher updatePitcherName(Long id, String firstName, String lastName) {
        Pitcher pitcher = this.pitcherRepository.findById(id).filter(p -> p.getGhostedDate() == 0).orElse(null);

        if(pitcher != null) {
            pitcher.setFirstName(firstName);
            pitcher.setLastName(lastName);
            return this.pitcherRepository.save(pitcher);
        }

        return null;
    }
}