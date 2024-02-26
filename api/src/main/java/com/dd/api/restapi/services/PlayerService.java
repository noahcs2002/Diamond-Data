package com.dd.api.restapi.services;

import com.dd.api.restapi.models.DefensivePlayer;
import com.dd.api.restapi.models.OffensivePlayer;
import com.dd.api.restapi.models.Player;
import com.dd.api.restapi.repositories.PlayerRepository;
import com.dd.api.restapi.requestmodels.PlayerUpdateRequestModel;
import com.dd.api.util.TruncatedSystemTimeProvider;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    @Autowired
    private final PlayerRepository repository;

    @Autowired
    private final OffensivePlayerService offensivePlayerService;

    @Autowired
    private final DefensivePlayerService defensivePlayerService;


    public PlayerService(PlayerRepository repository, OffensivePlayerService offensivePlayerService, DefensivePlayerService defensivePlayerService) {
        this.repository = repository;
        this.offensivePlayerService = offensivePlayerService;
        this.defensivePlayerService = defensivePlayerService;
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
    public Player createPlayer(Player player) {
        return this.repository.save(player);
    }

    @Transactional
    public Player createPlayer(OffensivePlayer offensivePlayer, DefensivePlayer defensivePlayer) {
        Player player = new Player();
        player.setOffensivePlayer(offensivePlayer);
        player.setDefensivePlayer(defensivePlayer);

        this.defensivePlayerService.createDefensivePlayer(defensivePlayer);
        this.offensivePlayerService.createPlayer(offensivePlayer);

        return this.repository.save(player);
    }

    @Transactional
    public boolean deletePlayer(Long id) {
        this.repository.findAll()
                .stream()
                .filter(p -> p.getGhostedDate() == 0)
                .filter(p -> p.getId().equals(id))
                .peek(p -> p.setGhostedDate(new TruncatedSystemTimeProvider().provideTime()));

        return true;
    }

    public Player update(Long id, PlayerUpdateRequestModel model) {
        OffensivePlayer offensivePlayer = model.manipulationRequestModel().offensivePlayer();
        DefensivePlayer defensivePlayer = model.manipulationRequestModel().defensivePlayer();

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
}