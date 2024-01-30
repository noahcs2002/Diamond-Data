package com.dd.api.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class TestConnector {
    
    private final SqlServerContext settings;
    
    public TestConnector() {
        this.settings = new SqlServerContext();
    }

    private void establishConnection() {
        try(Connection connection = DriverManager.getConnection(settings.getConnectionString(), settings.getUsername(), settings.getPassword())) {
            System.out.println("Connection established");
        }
        catch (Exception ex) {
            System.out.println("Error connecting to database: " + ex.getLocalizedMessage());
        }
    }
    
    private void performTestTransaction() {
        
        try(Connection connection = DriverManager.getConnection(settings.getConnectionString(), settings.getUsername(), settings.getPassword())) {
            String sql = "INSERT INTO sp24.testing VALUES ('testing')";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            
            sql = "DELETE FROM sp24.testing where entry='testing'";
            statement.executeUpdate(sql);
            
            System.out.println("Test transaction passed");
        }
        catch (Exception ex) {
            System.out.println("Transaction failed: " + ex.getLocalizedMessage());
        }
    }
    
    public void performTest() {
        establishConnection();
        performTestTransaction();
        System.out.println("System checks passed");
    }
    
}
