package com.dd.api.restapi.services;

import com.dd.api.auth.providers.AuthorizationService;
import com.dd.api.restapi.models.Team;
import com.dd.api.restapi.repositories.TeamRepository;
import com.dd.api.util.TruncatedSystemTimeProvider;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository repository;
    private final AuthorizationService authorizationService;

    @Autowired
    public TeamService(TeamRepository repository, AuthorizationService service) {
        this.repository = repository;
        this.authorizationService = service;
    }

    @Transactional
    public Team createTeam(Team team) {
        team.setUser(authorizationService.getNonTransientUser(team.getUser()));
        return this.repository.save(team);
    }

    @Transactional
    public Team getTeamById(Long id) {
        return this.repository.findById(id).orElse(null);
    }

    @Transactional
    public List<Team> getAllTeams(Long userId) {
        return this.repository.findAll()
                .stream()
                .filter(e -> e.getGhostedDate() == 0)
                .filter(e -> e.getUser().getId().equals(userId))
                .toList();
    }

    @Transactional
    public Team updateTeam(Long id, Team newTeam) {
        Team team = this.repository.findById(id).orElse(null);

        if(team != null) {
            team.setId(id);
            team.setUser(this.authorizationService.getNonTransientUser(newTeam.getUser()));
            team.setName(newTeam.getName());
            team.setGhostedDate(0);
        }

        return team;
    }

    @Transactional
    public boolean delete(Long id) {
        this.repository.findById(id).ifPresent(p -> {
            p.setGhostedDate(new TruncatedSystemTimeProvider().provideTime());
            this.repository.save(p);
        });
        return true;
    }
}