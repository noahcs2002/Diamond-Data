package com.dd.api.restapi.controllers;

import com.dd.api.restapi.models.Pitcher;
import com.dd.api.restapi.services.PitcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diamond-data/api/pitchers")
public class PitcherController {

    private final PitcherService service;
    
    @Autowired
    public PitcherController(PitcherService service) {
	this.service = service;
    }
    
    @RequestMapping("/get")
    @GetMapping
    public Pitcher get(@RequestParam Long id) {
	return this.service.getPitcherById(id);
    }
    
    @RequestMapping("/create")
    @PostMapping
    public Pitcher create(@RequestBody Pitcher pitcher) {
	return this.service.createPitcher(pitcher);
    }
    
    @RequestMapping("/update")
    @PutMapping
    public Pitcher update(@RequestParam Long id, @RequestBody Pitcher newModel) {
	return this.service.updatePitcher(id, newModel);
    }
    
    @RequestMapping("/delete")
    @DeleteMapping
    public boolean delete(@RequestParam Long id) {
	return this.service.deletePitcher(id);
    }
}