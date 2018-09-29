package com.codedito.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.codedito.userservice.model")
@SpringBootApplication
public class UserserviceApplication {

    public static void main(final String[] args) {
        SpringApplication.run(UserserviceApplication.class, args);
    }
}
