package com.dd.api.restapi.services;

import com.dd.api.restapi.models.Game;
import com.dd.api.restapi.repositories.GameRepository;
import com.dd.api.util.TruncatedSystemTimeProvider;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    @Autowired
    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Transactional
    public Game getGameById(Long id) {
        return this.gameRepository.findById(id).orElse(null);
    }

    @Transactional
    public List<Game> getAll() {
        return this.gameRepository.findAll()
                .stream()
                .filter(p -> p.getGhostedDate() == 0)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<Game> getGamesByTeam(Long teamId) {
        return this.gameRepository.findAll()
                .stream()
                .filter(p -> p.getGhostedDate() == 0)
                .filter(p -> p.getTeam().getId().equals(teamId))
                .collect(Collectors.toList());
    }

    @Transactional
    public Game updateGame(Long gameId, Game newModel) {
        newModel.setId(gameId);
        return this.gameRepository.save(newModel);
    }

    @Transactional
    public Game createGame(Game gameModel) {
        return this.gameRepository.save(gameModel);
    }

    @Transactional
    public boolean deleteGame(Long id) {
        this.gameRepository.findById(id).ifPresent(p -> {
            p.setGhostedDate(new TruncatedSystemTimeProvider().provideTime());
            this.gameRepository.save(p);
        });
        return true;
    }

}
