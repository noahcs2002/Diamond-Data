package com.dd.api.auth.controllers;

import com.dd.api.auth.models.User;
import com.dd.api.auth.providers.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diamond-data/api/auth")
public class AuthController {

    private final AuthorizationService service;

    @Autowired
    public AuthController(AuthorizationService service) {
        this.service = service;
    }

    @RequestMapping("/login")
    @GetMapping
    public User login(@RequestParam String email, @RequestParam String password) {
        return this.service.login(email, password);
    }

    @RequestMapping("/sign-up")
    @PostMapping
    public User signUp(@RequestBody User user) {
        return this.service.createUser(user);
    }

    @RequestMapping("/delete-account")
    @DeleteMapping
    public boolean deleteUser(@RequestParam Long id) {
        return this.service.deleteUser(id);
    }
}