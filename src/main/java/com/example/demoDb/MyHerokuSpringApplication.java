package com.example.demoDb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyHerokuSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyHerokuSpringApplication.class, args);
		System.out.println("Welcome fellas..!");
	}
}
