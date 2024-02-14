package com.dd.api.restapi.services;

import com.dd.api.restapi.models.Pitcher;
import com.dd.api.restapi.repositories.PitcherRepository;
import com.dd.api.util.TruncatedSystemTimeProvider;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PitcherService {
    
    private final PitcherRepository repository;
    
    @Autowired
    public PitcherService(PitcherRepository repository) {
	this.repository = repository;
    }
    
    @Transactional
    public Pitcher createPitcher(Pitcher pitcher) {
	return this.repository.saveAndFlush(pitcher);
    }
    
    @Transactional
    public Pitcher getPitcherById(Long id) {
	Optional<Pitcher> pitcher = this.repository.findById(id);
	return pitcher.orElse(null);
    }
    
    @Transactional
    public Iterable<Pitcher> getAll() {
	return this.repository.findAll();
    }
    
    @Transactional
    public Pitcher updatePitcher(Long id, Pitcher newModel) {
	Pitcher pitcher = this.repository.getReferenceById(id);
	newModel.setId(pitcher.getId());
	pitcher = newModel;
	this.repository.save(pitcher);
	return pitcher;
    }
    
    @Transactional
    public boolean deletePitcher(Long id) {
	Pitcher pitcher = this.repository.getReferenceById(id);
	pitcher.setGhostedDate(new TruncatedSystemTimeProvider().provideTime());
	this.repository.save(pitcher);
	return true;
	
    }
}
