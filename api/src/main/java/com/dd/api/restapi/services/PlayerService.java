package com.dd.api.restapi.services;

import com.dd.api.restapi.models.DefensivePlayer;
import com.dd.api.restapi.models.OffensivePlayer;
import com.dd.api.restapi.models.Player;
import com.dd.api.restapi.models.Team;
import com.dd.api.restapi.repositories.PlayerRepository;
import com.dd.api.restapi.requestmodels.PlayerUpdateRequestModel;
import com.dd.api.util.TruncatedSystemTimeProvider;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    @Autowired
    private final PlayerRepository repository;

    @Autowired
    private final OffensivePlayerService offensivePlayerService;

    @Autowired
    private final DefensivePlayerService defensivePlayerService;

    @Autowired
    private final TeamService teamService;


    public PlayerService(PlayerRepository repository, OffensivePlayerService offensivePlayerService, DefensivePlayerService defensivePlayerService, TeamService teamService) {
        this.repository = repository;
        this.offensivePlayerService = offensivePlayerService;
        this.defensivePlayerService = defensivePlayerService;
        this.teamService = teamService;
    }


    @Transactional
    public Player getPlayerById(Long id) {
        return this.repository.findAll()
                .stream()
                .filter(p -> p.getGhostedDate() == 0)
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Transactional
    public List<Player> getAll() {
        return this.repository.findAll()
                .stream()
                .filter(p -> p.getGhostedDate() == 0)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<Player> getByTeamId(Long id) {
        return this.repository.findAll()
                .stream()
                .filter(p -> p.getGhostedDate() == 0)
                .filter(p -> p.getDefensivePlayer().getTeam().getId().equals(id))
                .filter(p -> p.getOffensivePlayer().getTeam().getId().equals(id))
                .collect(Collectors.toList());
    }

    @Transactional
    public Player getByOffensivePlayerId(Long id) {
        return this.repository.findAll()
                .stream()
                .filter(p -> p.getGhostedDate() == 0)
                .filter(p -> p.getOffensivePlayer().getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Transactional
    public Player getByDefensivePlayerId(Long id) {
        return this.repository.findAll()
                .stream()
                .filter(p -> p.getGhostedDate() == 0)
                .filter(p -> p.getDefensivePlayer().getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Transactional
    public Player createPlayer(Player player, Team team) {
        player.getOffensivePlayer().setTeam(team);
        player.getDefensivePlayer().setTeam(team);
        this.defensivePlayerService.createDefensivePlayer(player.getDefensivePlayer());
        this.offensivePlayerService.createPlayer(player.getOffensivePlayer());
        return this.repository.save(player);
    }

    @Transactional
    public boolean deletePlayer(Long id) {
        List<Player> players = this.repository.findAll()
                .stream()
                .filter(p -> p.getId().equals(id))
                .toList();

        players.forEach(p -> {
            this.defensivePlayerService.deletePlayer(p.getDefensivePlayer().getId());
            this.offensivePlayerService.delete(p.getOffensivePlayer().getId());
            p.setGhostedDate(new TruncatedSystemTimeProvider().provideTime());
            this.repository.save(p);
        });

        List<Player> res = this.repository.findAll()
                .stream()
                .filter(p -> p.getGhostedDate()!=0)
                .toList();

        for(Player player : res) {
            if(player.getId().equals(id)) {
                return true;
            }
        }

        return false;
    }

    @Transactional
    public Player update(Long id, PlayerUpdateRequestModel model, Long teamId) {
        OffensivePlayer offensivePlayer = model.manipulationRequestModel().offensivePlayer();
        DefensivePlayer defensivePlayer = model.manipulationRequestModel().defensivePlayer();

        Team team = this.teamService.getTeamById(teamId);

        offensivePlayer.setTeam(team);
        defensivePlayer.setTeam(team);

        offensivePlayer.setId(model.offensiveId());
        defensivePlayer.setId(model.defensiveId());

        Player player = new Player();

        player.setDefensivePlayer(defensivePlayer);
        player.setOffensivePlayer(offensivePlayer);
        player.setId(id);

        this.defensivePlayerService.updateDefensivePlayer(model.offensiveId(), defensivePlayer);
        this.offensivePlayerService.update(model.offensiveId(), offensivePlayer);
        return this.repository.save(player);
    }

    @Transactional
    public Player changeFirstName(Long id, String newFirstName) {
       Player player = this.repository.findAll()
                .stream()
                .filter(p -> Objects.equals(p.getId(), id))
                .toList()
                .stream()
                .findFirst()
                .orElse(null);

       if (player != null) {
           player.setFirstName(newFirstName);
       }

       return player;
    }

    @Transactional
    public Player changeLastName(Long id, String newLastName) {
        Player player = this.repository.findAll()
                .stream()
                .filter(p -> Objects.equals(p.getId(), id))
                .toList()
                .stream()
                .findFirst()
                .orElse(null);

        if (player != null) {
            player.setLastName(newLastName);
        }

        return player;
    }
}