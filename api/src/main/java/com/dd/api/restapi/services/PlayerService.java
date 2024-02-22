package com.dd.api.restapi.services;

import com.dd.api.restapi.models.DefensivePlayer;
import com.dd.api.restapi.models.OffensivePlayer;
import com.dd.api.restapi.models.Player;
import com.dd.api.restapi.repositories.PlayerRepository;
import com.dd.api.util.TruncatedSystemTimeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private final PlayerRepository repository;

    @Autowired
    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    public Player getPlayerById(Long id) {
        return this.repository.findAll()
                .stream()
                .filter(p -> p.getGhostedDate() == 0)
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Player> getAll() {
        return this.repository.findAll()
                .stream()
                .filter(p -> p.getGhostedDate() == 0)
                .collect(Collectors.toList());
    }

    public List<Player> getByTeamId(Long id) {
        return this.repository.findAll()
                .stream()
                .filter(p -> p.getGhostedDate() == 0)
                .filter(p -> p.getDefensivePlayer().getTeam().getId().equals(id))
                .filter(p -> p.getOffensivePlayer().getTeam().getId().equals(id))
                .collect(Collectors.toList());
    }

    public Player getByOffensivePlayerId(Long id) {
        return this.repository.findAll()
                .stream()
                .filter(p -> p.getGhostedDate() == 0)
                .filter(p -> p.getOffensivePlayer().getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Player getByDefensivePlayerId(Long id) {
        return this.repository.findAll()
                .stream()
                .filter(p -> p.getGhostedDate() == 0)
                .filter(p -> p.getDefensivePlayer().getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Player createPlayer(Player player) {
        return this.repository.save(player);
    }

    public Player createPlayer(OffensivePlayer offensivePlayer, DefensivePlayer defensivePlayer) {
        Player player = new Player();
        player.setOffensivePlayer(offensivePlayer);
        player.setDefensivePlayer(defensivePlayer);
        return this.repository.save(player);
    }

    public boolean deletePlayer(Long id) {
        this.repository.findAll()
                .stream()
                .filter(p -> p.getGhostedDate() == 0)
                .filter(p -> p.getId().equals(id))
                .peek(p -> p.setGhostedDate(new TruncatedSystemTimeProvider().provideTime()));

        return true;
    }

}
