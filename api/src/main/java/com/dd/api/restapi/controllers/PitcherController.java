package com.dd.api.restapi.controllers;

import com.dd.api.database.Context;
import com.dd.api.restapi.factories.PitcherFactory;
import com.dd.api.restapi.models.Pitcher;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;

@RestController
@RequestMapping("/diamond-data/api/pitchers")
public class PitcherController {

    private final Context context = new Context();

    @GetMapping
    @RequestMapping("xyz")
    public String connect() {
        return "PitcherController Online";
    }

    @PostMapping
    public Pitcher createPitcher(@RequestBody Pitcher pitcher) {
        try (Connection connection = DriverManager.getConnection(context.getConnectionString(), context.getUsername(), context.getPassword())) {
            return PitcherFactory.createPitcher(pitcher, connection);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}