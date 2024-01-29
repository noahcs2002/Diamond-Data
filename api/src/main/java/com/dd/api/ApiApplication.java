package com.dd.api;

import com.dd.api.database.Context;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
	    Context settings = new Context();
	    System.out.println("Username: " + settings.getUsername());
	    System.out.println("Password: " + settings.getPassword());
	    System.out.println("Connection String: " + settings.getConnectionString());
	    SpringApplication.run(ApiApplication.class, args);
	}

}
