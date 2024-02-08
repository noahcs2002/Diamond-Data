package com.dd.api.restapi.factories;

import com.dd.api.restapi.builders.TeamBuilder;
import com.dd.api.restapi.models.Color;
import com.dd.api.restapi.models.Team;
import com.dd.api.util.TruncatedSystemTimeProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TeamFactory {

    public static Team getTeam(UUID teamId, Connection connection) {
        String sql = "SELECT * FROM sp24.dd_teams WHERE id=? AND ghosted_date=0";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, teamId.toString());
            ResultSet set = statement.executeQuery();

            while(set.next()) {
                Team team = new TeamBuilder().setAccountId(UUID.fromString(set.getString("accountId")))
                        .setName(set.getString("name"))
                        .setPrimaryColour(new Color(set.getString("primaryColor")))
                        .setSecondaryColour(new Color(set.getString("secondaryColor")))
                        .setAccentColour(new Color(set.getString("accentColor")))
                        .createTeam();

                team.setId(teamId);
                return team;
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
            INSERT INTO sp24.dd_teams (id, accountId, name, primaryColor, secondaryColor, accentColor) VALUES  (?, ?, ?, ?, ?, ?);
        """;

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, team.getId().toString());
            statement.setString(2, team.getAccountId().toString());
            statement.setString(3, team.getName());
            statement.setString(4, team.getPrimaryColor().toString());
            statement.setString(5, team.getSecondaryColor().toString());
            statement.setString(6, team.getAccentColor().toString());

            statement.executeUpdate();
            return team;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public static boolean delete(UUID teamId, Connection connection) {
        String sql = "UPDATE sp24.dd_teams SET ghosted_date=? WHERE id=?";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, new TruncatedSystemTimeProvider().provideTime());
            statement.setString(2, teamId.toString());
            statement.executeUpdate();
            return true;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static Team edit(UUID id, Team team, Connection connection) {
        String sql = """
            UPDATE sp24.dd_teams SET name=?, primaryColor=?, secondaryColor=?, accentColor=? WHERE id=?
        """;

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, team.getName());
            statement.setString(2, team.getPrimaryColor().toString());
            statement.setString(3, team.getSecondaryColor().toString());
            statement.setString(4, team.getAccentColor().toString());
            statement.setString(5, id.toString());
            statement.executeUpdate();
            return team;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public static List<Team> getTeamByAccountId(UUID accountId, Connection connection) {
        String sql = "SELECT * FROM sp24.dd_teams WHERE accountId=? AND ghosted_date=0";
        List<Team> teams = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, accountId.toString());
            ResultSet set = statement.executeQuery();

            while(set.next()) {
                teams.add(new TeamBuilder().setAccountId(UUID.fromString(set.getString("accountId")))
                        .setName(set.getString("name"))
                        .setPrimaryColour(new Color(set.getString("primaryColor")))
                        .setSecondaryColour(new Color(set.getString("secondaryColor")))
                        .setAccentColour(new Color(set.getString("accentColor")))
                        .createTeam());
            }
            return teams;
        }
        catch (Exception ex)  {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public static List<Team> getAllTeams(Connection connection){
        String sql = "SELECT * FROM sp24.dd_teams WHERE ghosted_date=0";
        List<Team> teams = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet set = statement.executeQuery();

            while(set.next()) {
                teams.add(new TeamBuilder().setAccountId(UUID.fromString(set.getString("accountId")))
                        .setName(set.getString("name"))
                        .setPrimaryColour(new Color(set.getString("primaryColor")))
                        .setSecondaryColour(new Color(set.getString("secondaryColor")))
                        .setAccentColour(new Color(set.getString("accentColor")))
                        .createTeam());
            }
            return teams;
        }
        catch (Exception ex)  {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
