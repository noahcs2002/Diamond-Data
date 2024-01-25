package com.dd.api.restapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diamond-data/api/teams")
public class TeamController {
    
    @GetMapping
    @RequestMapping("xyz")
    public String connect() {
	return "Team Controller Online";
    }
    
}
