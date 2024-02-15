package com.dd.api.restapi.services;

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
    
    @Autowired
    public TeamService(TeamRepository repository) {
	this.repository = repository;
    }
    
    @Transactional
    public Team createTeam(Team team) {
	return this.repository.save(team);
    }
    
    @Transactional
    public Team getTeamById(Long id) {
	return this.repository.findById(id).orElse(null);
    }
    
    @Transactional
    public List<Team> getAllTeams() {
	return this.repository.findAll()
	    .stream()
	    .filter(e -> e.getGhostedDate() == 0)
	    .toList();
    }
    
    @Transactional
    public Team updateTeam(Long id, Team newTeam) {
	this.getTeamById(id);
	newTeam.setId(id);
	return this.repository.save(newTeam);
    }
    
    @Transactional
    public boolean delete(Long id) {
	Team team = this.repository.getReferenceById(id);
	team.setGhostedDate(new TruncatedSystemTimeProvider().provideTime());
	this.repository.save(team);
	return true;
    }
}