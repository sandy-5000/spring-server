package com.darkube.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		try {
			Dotenv dotenv = Dotenv.load();
			System.setProperty("port", dotenv.get("PORT"));
			System.setProperty("salt", dotenv.get("SALT"));
			System.setProperty("mongo", dotenv.get("MONGOBD"));
			System.setProperty("database", dotenv.get("DATABASE"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
