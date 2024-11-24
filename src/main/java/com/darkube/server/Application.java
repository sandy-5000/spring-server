package com.darkube.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		try {
			Dotenv dotenv = Dotenv.load();
			System.setProperty("port", dotenv.get("PORT"));
			System.setProperty("jwt", dotenv.get("JWT_SECRET"));
			System.setProperty("mongo", dotenv.get("MONGODB"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		SpringApplication.run(Application.class, args);
	}

}
