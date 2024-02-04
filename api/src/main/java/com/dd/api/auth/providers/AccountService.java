package com.dd.api.auth.providers;

import com.dd.api.auth.builders.AccountBuilder;
import com.dd.api.auth.models.Account;
import com.dd.api.util.TruncatedSystemTimeProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

public class AccountService {

    public static Account createAccount(Connection connection) {
        final Account account = new AccountBuilder().createAccount();

        String sql = "INSERT INTO sp24.dd_accounts (id, userCount, ghosted_date) VALUES (?, 0, 0)";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, account.getId().toString());
            statement.executeUpdate();
            return account;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public static boolean suspendAccount(UUID id, Connection connection) {

        String sql = "UPDATE sp24.dd_accounts SET ghosted_date=? WHERE id=?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, new TruncatedSystemTimeProvider().provideTime());
            statement.setString(2, id.toString());
            statement.executeUpdate();
            return true;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    /**
     * To be called on user creation
     * @param id The id of the account to increment the userCount
     * @param connection The connection on which to implement the change
     * @return The modified user
     */
    public static Account addUser(UUID id, Connection connection) {
        String sql = "UPDATE sp24.dd_accounts SET userCount=userCount+1 WHERE id=? AND ghosted_date=0";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id.toString());
            statement.executeUpdate();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        sql = "SELECT * FROM sp24.dd_accounts WHERE id=? AND ghosted_date=0";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id.toString());
            ResultSet set = statement.executeQuery();
            while(set.next()) {
                return new AccountBuilder().setId(UUID.fromString(set.getString("id"))).setUserCount(set.getInt("userCount")).createAccount();
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return null;
    }

    /**
     * To be called when deleting a user.
     * @return Modified account
     */
    public static Account removeUserFromAccount(UUID accountId, Connection connection) {

        String sql = "UPDATE sp24.dd_accounts SET userCount=userCount-1 WHERE id=? AND ghosted_date=0";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, accountId.toString());
            statement.executeUpdate();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        sql = "SELECT * FROM sp24.dd_accounts WHERE id=? AND ghosted_date=0";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, accountId.toString());
            ResultSet set = statement.executeQuery();
            while(set.next()) {
                return new AccountBuilder().setId(UUID.fromString(set.getString("id"))).setUserCount(set.getInt("userCount")).createAccount();
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return null;
    }
}
