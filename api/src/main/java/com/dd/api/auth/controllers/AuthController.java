package com.dd.api.auth.controllers;

import com.dd.api.auth.models.User;
import com.dd.api.auth.providers.AuthorizationService;
import com.dd.api.restapi.models.Team;
import com.dd.api.restapi.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diamond-data/api/auth")
public class AuthController {

    @Autowired
    private final AuthorizationService service;

    @Autowired
    private final TeamService teamService;

    @Autowired
    public AuthController(AuthorizationService service, TeamService teamService) {
        this.service = service;
        this.teamService = teamService;
    }

    @RequestMapping("/login")
    @GetMapping
    public User login(@RequestParam String email, @RequestParam String password) {
        return this.service.login(email, password);
    }

    @RequestMapping("/sign-up")
    @PostMapping
    public User signUp(@RequestBody User user, @RequestParam String teamName) {
        
        Team team = new Team();
        User res = this.service.createUser(user);
        team.setName(teamName);
        team.setUser(res);

        this.teamService.createTeam(team);
        return res;
    }

    @RequestMapping("/delete-account")
    @DeleteMapping
    public boolean deleteUser(@RequestParam Long id) {
        return this.service.deleteUser(id);
    }

    @RequestMapping("/change-name")
    @PutMapping
    public User changeName(@RequestParam Long userId, @RequestParam String newName) {
        return this.service.changeName(userId, newName);
    }

    @RequestMapping("/change-phone-number")
    @PutMapping
    public User changePhoneNumber(@RequestParam Long userId, @RequestParam String phoneNumber) {
        return this.service.changePhoneNumber(userId, phoneNumber);
    }


}