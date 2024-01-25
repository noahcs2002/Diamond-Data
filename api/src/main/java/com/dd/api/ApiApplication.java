package com.dd.api;

import com.dd.api.database.ConnectionSettings;
import com.dd.api.util.PropertyFileReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
	    ConnectionSettings settings = new ConnectionSettings();
	    System.out.println("Username: " + settings.getUsername());
	    System.out.println("Password: " + settings.getPassword());
	    System.out.println("Connection String: " + settings.getConnectionString());
	    SpringApplication.run(ApiApplication.class, args);
	}

}
