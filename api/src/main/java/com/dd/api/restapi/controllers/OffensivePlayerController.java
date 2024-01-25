package com.dd.api.restapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diamond-data/api/offensive-players")
public class OffensivePlayerController {
    
    @GetMapping
    @RequestMapping("xyz")
    public String connect() {
	return "OffensivePlayerController Online";
    }
    
}
