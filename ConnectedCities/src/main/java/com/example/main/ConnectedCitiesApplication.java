package com.example.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.service"})
public class ConnectedCitiesApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(ConnectedCitiesApplication.class, args);
	}

}
