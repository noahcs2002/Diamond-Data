package com.dd.api.auth.providers;

import com.dd.api.auth.models.User;
import com.dd.api.auth.security.SecurityUtilityTool;
import org.apache.commons.codec.binary.Base64;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

public class AuthorizationService {

    public static User attemptLogin(String username, String password, Connection connection) {
        String sql = """
            SELECT * FROM sp24.dd_users WHERE username=? AND passwordHash=? AND ghosted_date=0;
        """;
        String protectedPassword = SecurityUtilityTool.protect(password);
        protectedPassword = Base64.encodeBase64String(protectedPassword.getBytes());

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, protectedPassword);
            ResultSet set = statement.executeQuery();

            while(set.next()) {
                return new User(
                        UUID.fromString(set.getString("id")),
                        set.getString("username"),
                        "", // Empty because passwords and hashes are never sent back
                        UUID.fromString(set.getString("accountId"))
                );
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }

        return null;
    }

    public static User register(User modelToCreate, Connection connection) {

        String protectedPassword = SecurityUtilityTool.protect(modelToCreate.getPassword());
        protectedPassword = Base64.encodeBase64String(protectedPassword.getBytes());

        String sql = """
            INSERT INTO sp24.dd_users
                (id, username, passwordHash, ghosted_date, accountId)
                VALUES (?, ?, ?, ?, ?);
        """;

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, modelToCreate.getId().toString());
            statement.setString(2, modelToCreate.getUsername());
            statement.setString(3, protectedPassword);
            statement.setLong(4, 0);
            statement.setString(5, modelToCreate.getAccountId().toString());
            statement.executeUpdate();
            AccountService.addUser(modelToCreate.getAccountId(), connection);
            return modelToCreate;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public static User forgotPassword(String username, String newPassword, Connection connection) {
        String protectedPassword = SecurityUtilityTool.protect(newPassword);
        protectedPassword = Base64.encodeBase64String(protectedPassword.getBytes());

        String sql = """
           UPDATE sp24.dd_users SET passwordHash=? WHERE username=? AND ghosted_date=0;
        """;

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, protectedPassword);
            statement.setString(2, username);
            statement.executeUpdate();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }

        sql = "SELECT * FROM sp24.dd_users WHERE username=? AND ghosted_date=0";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet set = statement.executeQuery();
            while(set.next()) {
                return new User(
                        UUID.fromString(set.getString("id")),
                        set.getString("username"),
                        "", // Empty because passwords and hashes are never sent back
                        UUID.fromString(set.getString("accountId"))
                );
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return null;
    }
}
