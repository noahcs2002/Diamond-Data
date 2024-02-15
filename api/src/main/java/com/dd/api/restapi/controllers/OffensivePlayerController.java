package com.dd.api.restapi.controllers;

import com.dd.api.restapi.models.OffensivePlayer;
import com.dd.api.restapi.services.OffensivePlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diamond-data/api/offensive-players")
public class OffensivePlayerController {
    
    private final OffensivePlayerService service;
    
    @Autowired
    public OffensivePlayerController(OffensivePlayerService service) {
	this.service = service;
    }
    
    @RequestMapping("/get")
    @GetMapping
    public OffensivePlayer get(@RequestParam Long id) {
	return this.service.getOffensivePlayer(id);
    }
    
    @RequestMapping("/get-all")
    @GetMapping
    public List<OffensivePlayer> getAll() {
	return this.service.getAll();
    }
    
    @RequestMapping("/get-by-team")
    @GetMapping
    public List<OffensivePlayer> getByTeam(@RequestParam Long teamId) {
	return this.service.getByTeam(teamId);
    }
    
    @RequestMapping("/update")
    @PutMapping
    public OffensivePlayer update(@RequestParam Long id, @RequestBody OffensivePlayer player) {
	return this.service.update(id, player);
    }
    
    @RequestMapping("/delete")
    @DeleteMapping
    public boolean delete(@RequestParam Long id) {
	return this.service.delete(id);
    }
    
    @RequestMapping("/create")
    @PostMapping
    public OffensivePlayer create(@RequestBody OffensivePlayer player) {
	return this.service.createPlayer(player);
    }
}