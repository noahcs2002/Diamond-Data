package com.dd.api.restapi.services;

import com.dd.api.auth.providers.AuthorizationRepository;
import com.dd.api.restapi.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MaintenanceProvider {

    private final PitcherRepository pitcherRepository;
    private final PlayerRepository playerRepository;
    private final OffensivePlayerRepository offensivePlayerRepository;
    private final DefensivePlayerRepository defensivePlayerRepository;
    private final AuthorizationRepository authorizationRepository;
    private final LineupRepository lineupRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public MaintenanceProvider(PitcherRepository pitcherRepository, PlayerRepository playerRepository, OffensivePlayerRepository offensivePlayerRepository, DefensivePlayerRepository defensivePlayerRepository,  AuthorizationRepository authorizationRepository, LineupRepository lineupRepository, TeamRepository teamRepository) {
        this.pitcherRepository = pitcherRepository;
        this.playerRepository = playerRepository;
        this.offensivePlayerRepository = offensivePlayerRepository;
        this.defensivePlayerRepository = defensivePlayerRepository;
        this.authorizationRepository = authorizationRepository;
        this.lineupRepository = lineupRepository;
        this.teamRepository = teamRepository;
    }

    public boolean clearDeletedRecords() {
        try {

            this.authorizationRepository.findAll()
                    .stream()
                    .filter(r -> r.getGhostedDate() != 0)
                    .peek(authorizationRepository::delete);

            this.lineupRepository.findAll()
                    .stream()
                    .filter(r -> r.getGhostedDate() != 0)
                    .peek(lineupRepository::delete);

            this.teamRepository.findAll()
                    .stream()
                    .filter(r -> r.getGhostedDate() != 0)
                    .peek(teamRepository::delete);

            this.pitcherRepository.findAll()
                    .stream()
                    .filter(r -> r.getGhostedDate() != 0)
                    .peek(pitcherRepository::delete);

            this.offensivePlayerRepository.findAll()
                    .stream()
                    .filter(r -> r.getGhostedDate() != 0)
                    .peek(offensivePlayerRepository::delete);

            this.defensivePlayerRepository.findAll()
                    .stream()
                    .filter(r -> r.getGhostedDate() != 0)
                    .peek(defensivePlayerRepository::delete);

            this.playerRepository.findAll()
                    .stream()
                    .filter(r -> r.getGhostedDate() != 0)
                    .peek(playerRepository::delete);

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }
}
