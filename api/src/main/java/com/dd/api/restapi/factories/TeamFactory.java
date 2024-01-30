package com.dd.api.restapi.factories;

import com.dd.api.database.Context;
import com.dd.api.restapi.builders.TeamBuilder;
import com.dd.api.restapi.models.Color;
import com.dd.api.restapi.models.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

public class TeamFactory {


    public static Team getTeam(UUID teamId, Connection connection) {
        String sql = "SELECT * FROM sp24.dd_teams WHERE id=? AND ghosted_date=0";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, teamId.toString());
            ResultSet set = statement.executeQuery();

            while(set.next()) {
                return new TeamBuilder().setAccountId(UUID.fromString(set.getString("accountId")))
                        .setId(UUID.fromString(set.getString("id")))
                        .setName(set.getString("name"))
                        .setPrimaryColour(new Color(set.getString("primaryColor")))
                        .setSecondaryColour(new Color(set.getString("secondaryColor")))
                        .setAccentColour(new Color(set.getString("accentColor")))
                        .createTeam();
            }
        }
        catch (Exception ex)  {
            System.out.println(ex.getMessage());
            return null;
        }
        return null;
    }

    public static Team createTeam(Team team, Connection connection) {
        String sql = """
            INSERT INTO sp24.dd_teams (id, accountId, name, primaryColor, secondaryColor, accentColor) VALUES ?, ?, ?, ?, ?, ?
        """;

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, team.getId().toString());
            statement.setString(2, team.getAccountId().toString());
            statement.setString(3, team.getName());
            statement.setString(4, team.getPrimaryColour().toString());
            statement.setString(5, team.getSecondaryColour().toString());
            statement.setString(6, team.getAccentColour().toString());

            statement.executeUpdate();
            return team;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
