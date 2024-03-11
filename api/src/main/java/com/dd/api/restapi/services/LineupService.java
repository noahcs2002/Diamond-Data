package com.dd.api.restapi.services;

import com.dd.api.restapi.models.DefensivePlayer;
import com.dd.api.restapi.models.Lineup;
import com.dd.api.restapi.models.Pitcher;
import com.dd.api.restapi.models.Team;
import com.dd.api.restapi.repositories.LineupRepository;
import com.dd.api.restapi.requestmodels.CreateLineupRequestModel;
import com.dd.api.util.TruncatedSystemTimeProvider;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineupService {

    @Autowired
    private final LineupRepository repository;

    @Autowired
    private final PitcherService pitcherService;

    @Autowired
    private final DefensivePlayerService defensivePlayerService;

    @Autowired
    private final TeamService teamService;

    @Autowired
    public LineupService(LineupRepository repository,
                         DefensivePlayerService defensivePlayerService,
                         PitcherService pitcherService,
                         TeamService teamService) {
        this.repository = repository;
        this.pitcherService = pitcherService;
        this.defensivePlayerService = defensivePlayerService;
        this.teamService = teamService;

    }

    @Transactional
    public Lineup createLineup(CreateLineupRequestModel model) {
        DefensivePlayer firstBase = this.defensivePlayerService.getDefensivePlayer(model.firstBase());
        DefensivePlayer secondBase = this.defensivePlayerService.getDefensivePlayer(model.secondBase());
        DefensivePlayer thirdBase = this.defensivePlayerService.getDefensivePlayer(model.thirdBase());
        DefensivePlayer shortstop = this.defensivePlayerService.getDefensivePlayer(model.shortstop());
        DefensivePlayer leftField = this.defensivePlayerService.getDefensivePlayer(model.leftField());
        DefensivePlayer rightField = this.defensivePlayerService.getDefensivePlayer(model.rightField());
        DefensivePlayer centerField = this.defensivePlayerService.getDefensivePlayer(model.centerField());
        DefensivePlayer catcher = this.defensivePlayerService.getDefensivePlayer(model.catcher());
        Pitcher pitcher = this.pitcherService.getPitcherById(model.pitcher());
        Team team = this.teamService.getTeamById(model.team());

        Lineup lineup = new Lineup();
        lineup.setFirstBase(firstBase);
        lineup.setSecondBase(secondBase);
        lineup.setThirdBase(thirdBase);
        lineup.setShortstop(shortstop);
        lineup.setLeftField(leftField);
        lineup.setRightField(rightField);
        lineup.setCenterField(centerField);
        lineup.setCatcher(catcher);
        lineup.setPitcher(pitcher);
        lineup.setTeam(team);
        return this.repository.save(lineup);
    }

    @Transactional
    public Lineup getLineupById(Long id) {
        return this.repository.findById(id).orElse(null);
    }

    @Transactional
    public List<Lineup> getAll() {
        return this.repository.findAll()
                .stream()
                .filter(l -> l.getGhostedDate() == 0)
                .toList();
    }

    @Transactional
    public List<Lineup> getByTeamId(Long teamId) {
        return this.repository.findAll()
                .stream()
                .filter(l -> l.getTeam().getId().equals(teamId))
                .filter(l -> l.getGhostedDate() == 0)
                .toList();
    }

    @Transactional
    public Lineup updateFirstBase(Long id, Long playerId) {
        return this.repository.findAll()
                .stream()
                .filter(l -> l.getId().equals(id))
                .peek(l -> l.setFirstBase(this.defensivePlayerService.getDefensivePlayer(playerId)))
                .findFirst()
                .orElse(null);
    }

    @Transactional
    public Lineup updateSecondBase(Long id, Long playerId) {
        return this.repository.findAll()
                .stream()
                .filter(l -> l.getId().equals(id))
                .peek(l -> l.setSecondBase(this.defensivePlayerService.getDefensivePlayer(playerId)))
                .toList()
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Transactional
    public Lineup updateThirdBase(Long id, Long playerId) {
        return this.repository.findAll()
                .stream()
                .filter(l -> l.getId().equals(id))
                .peek(l -> l.setThirdBase(this.defensivePlayerService.getDefensivePlayer(playerId)))
                .toList()
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Transactional
    public Lineup updateShortStop(Long id, Long playerId) {
        return this.repository.findAll()
                .stream()
                .filter(l -> l.getId().equals(id))
                .peek(l -> l.setShortstop(this.defensivePlayerService.getDefensivePlayer(playerId)))
                .toList()
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Transactional
    public Lineup updateLeftField(Long id, Long playerId) {
        return this.repository.findAll()
                .stream()
                .filter(l -> l.getId().equals(id))
                .peek(l -> l.setLeftField(this.defensivePlayerService.getDefensivePlayer(playerId)))
                .toList()
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Transactional
    public Lineup updateCenterField(Long id, Long playerId) {
        return this.repository.findAll()
                .stream()
                .filter(l -> l.getId().equals(id))
                .peek(l -> l.setCenterField(this.defensivePlayerService.getDefensivePlayer(playerId)))
                .toList()
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Transactional
    public Lineup updateRightField(Long id, Long playerId) {
        return this.repository.findAll()
                .stream()
                .filter(l -> l.getId().equals(id))
                .peek(l -> l.setRightField(this.defensivePlayerService.getDefensivePlayer(playerId)))
                .toList()
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Transactional
    public Lineup updatePitcher(Long id, Long playerId) {
        return this.repository.findAll()
                .stream()
                .filter(l -> l.getId().equals(id))
                .peek(l -> l.setPitcher(this.pitcherService.getPitcherById(playerId)))
                .toList()
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Transactional
    public Lineup updateCatcher(Long id, Long playerId) {
        return this.repository.findAll()
                .stream()
                .filter(l -> l.getId().equals(id))
                .peek(l -> l.setCatcher(this.defensivePlayerService.getDefensivePlayer(playerId)))
                .toList()
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Transactional
    public boolean deleteLineup(Long id) {
        this.repository.findAll()
                .stream()
                .filter(l -> l.getId().equals(id))
                .peek(l -> l.setGhostedDate(new TruncatedSystemTimeProvider().provideTime()));

        return true;
    }
}