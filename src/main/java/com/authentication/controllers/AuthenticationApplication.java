package com.authentication.controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example")
public class AuthenticationApplication {

	public static void main(String[] args) {

		SpringApplication.run(AuthenticationApplication.class, args);

	}

}
