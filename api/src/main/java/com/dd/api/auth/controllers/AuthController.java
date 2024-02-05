package com.dd.api.auth.controllers;

import com.dd.api.auth.models.Account;
import com.dd.api.auth.models.User;
import com.dd.api.auth.providers.AccountService;
import com.dd.api.auth.providers.AuthorizationService;
import com.dd.api.database.Context;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.UUID;

@RestController
@RequestMapping("/diamond-data/api/auth")
public class AuthController {

    private final Context context = new Context();

    @GetMapping
    @RequestMapping("xyz")
    public String connected() {
        System.out.println("Endpoint hit");
        return "AuthController Online";
    }

    @PostMapping
    @RequestMapping("/create-user")
    public User createNewUser(@RequestBody User user) {
        try(Connection connection = DriverManager.getConnection(context.getConnectionString(), context.getUsername(), context.getPassword())) {
            return AuthorizationService.register(user, connection);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @GetMapping
    @RequestMapping("/login")
    public User attemptLogin(@RequestParam String username, @RequestParam String password) {
        try(Connection connection = DriverManager.getConnection(context.getConnectionString(), context.getUsername(), context.getPassword())) {
            return AuthorizationService.attemptLogin(username, password, connection);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @PutMapping
    @RequestMapping("/forgot-password")
    public User forgotPassword(@RequestParam String username, @RequestParam String newPassword) {
        try(Connection connection = DriverManager.getConnection(context.getConnectionString(), context.getUsername(), context.getPassword())) {
            return AuthorizationService.forgotPassword(username, newPassword, connection);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @GetMapping
    @RequestMapping("/verify")
    public boolean verifyIdentity(@RequestParam String username, @RequestParam int[] code) {
        // Eventually the API will have the ability to email users to verify their identity
        // By entering their email, and having a 6-digit code emailed to them.
        // We can lock password resetting behind this for security purposes.
        // Right now this is empty because this feature is coming later.
        // - NS
        return true;
    }

    @PostMapping
    @RequestMapping("/create-account")
    public Account createAccount() {
        try(Connection connection = DriverManager.getConnection(context.getConnectionString(), context.getUsername(), context.getPassword())) {
            return AccountService.createAccount(connection);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @DeleteMapping
    @RequestMapping("/suspend-account")
    public boolean suspendAccount(@RequestParam UUID id) {
        try(Connection connection = DriverManager.getConnection(context.getConnectionString(), context.getUsername(), context.getPassword())) {
            return AccountService.suspendAccount(id, connection);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @PutMapping
    @RequestMapping("/debug/add-user-to-account")
    public Account addUserToAccount(@RequestParam UUID id) {
        try(Connection connection = DriverManager.getConnection(context.getConnectionString(), context.getUsername(), context.getPassword())) {
            return AccountService.addUser(id, connection);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @PutMapping
    @RequestMapping("/debug/remove-user-from-account")
    public Account removeUserFromAccount(@RequestParam UUID id) {
        try(Connection connection = DriverManager.getConnection(context.getConnectionString(), context.getUsername(), context.getPassword())) {
            return AccountService.removeUserFromAccount(id, connection);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}