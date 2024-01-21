package com.dd.api.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Executor {
    public static boolean executeUpdate(String sql, Connection connection) {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            return true;

        }
        catch (Exception ex) {
            System.out.println("Error executing statement: " + ex);
            return false;
        }
    }

    public static ResultSet executeQuery(String sql, Connection connection) {
        try(Statement statement = connection.createStatement()) {
            return statement.executeQuery(sql);
        }
        catch (Exception ex) {
            System.out.println("Error with statement: " + ex);
            return null;
        }
    }
}
