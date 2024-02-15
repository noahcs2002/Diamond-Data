package com.dd.api.restapi.services;

import com.dd.api.restapi.models.Pitcher;
import com.dd.api.restapi.repositories.PitcherRepository;
import com.dd.api.restapi.repositories.TeamRepository;
import com.dd.api.util.TruncatedSystemTimeProvider;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PitcherService {
    
    @Autowired
    private final PitcherRepository pitcherRepository;
    
    
    @Autowired
    public PitcherService(PitcherRepository pitcherRepository) {
	this.pitcherRepository = pitcherRepository;
    }
    
    @Transactional
    public Pitcher createPitcher(Pitcher pitcher) {
	return this.pitcherRepository.saveAndFlush(pitcher);
    }
    
    @Transactional
    public Pitcher getPitcherById(Long id) {
	Optional<Pitcher> pitcher = this.pitcherRepository.findById(id);
	return pitcher.orElse(null);
    }
    
    @Transactional
    public List<Pitcher> getAll() {
	List<Pitcher> pitchers = this.pitcherRepository.findAll();
	return pitchers.stream().filter(p -> p.getGhostedDate() == 0).toList();
    }
    
    @Transactional
    public Pitcher updatePitcher(Long id, Pitcher newModel) {
	Pitcher pitcher = this.pitcherRepository.getReferenceById(id);
	newModel.setId(pitcher.getId());
	pitcher = newModel;
	this.pitcherRepository.save(pitcher);
	return pitcher;
    }
    
    @Transactional
    public boolean deletePitcher(Long id) {
	Pitcher pitcher = this.pitcherRepository.getReferenceById(id);
	pitcher.setGhostedDate(new TruncatedSystemTimeProvider().provideTime());
	this.pitcherRepository.save(pitcher);
	return true;
    }
    
    public List<Pitcher> getPitchersByTeam(Long teamId) {
	List<Pitcher> pitchers = this.pitcherRepository.findAll();
	return pitchers.stream()
	    .filter(p -> p.getGhostedDate() == 0)
	    .filter(p -> Objects.equals(p.getTeam().getId(), teamId))
	    .toList();
    }
}