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