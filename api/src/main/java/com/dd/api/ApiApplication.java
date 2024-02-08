package com.dd.api;

import com.dd.api.database.TestConnector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class ApiApplication {
    public static void main(String[] args) {
        new TestConnector().performTest();
        SpringApplication.run(ApiApplication.class, args);
    }
}
