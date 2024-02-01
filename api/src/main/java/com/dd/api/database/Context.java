package com.dd.api.database;

import com.dd.api.util.PropertyFileReader;

import java.util.HashMap;

public class Context {
    private final String username;
    private final String password;
    private final String connectionString;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getConnectionString() {
        return connectionString;
    }

    public Context() {
        PropertyFileReader reader = new PropertyFileReader("src/main/resources/db_connection.lock");
        HashMap<String, String> properties = reader.getContents();

        try {
            this.username = properties.get("username");
            this.password = properties.get("password");
            this.connectionString = properties.get("connection_string");
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}