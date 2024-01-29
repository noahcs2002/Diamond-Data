package com.dd.api;

import com.dd.api.database.ConnectionSettings;
import com.dd.api.database.TestConnector;
import com.dd.api.util.PropertyFileReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
	    
	    new TestConnector().performTest();
	    SpringApplication.run(ApiApplication.class, args);
	}

}
