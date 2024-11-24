package com.darkube.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		try {
			String env = System.getenv("ENV");
			System.out.println("Environment: " + env);
			if (env == null || env.equals("development")) {
				Dotenv dotenv = Dotenv.load();
				System.setProperty("port", dotenv.get("PORT"));
				System.setProperty("jwt", dotenv.get("JWT_SECRET"));
				System.setProperty("mongo", dotenv.get("MONGODB"));
			} else if (env.equals("production")) {
				System.setProperty("port", System.getenv("PORT"));
				System.setProperty("jwt", System.getenv("JWT_SECRET"));
				System.setProperty("mongo", System.getenv("MONGODB"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		SpringApplication.run(Application.class, args);
	}

}
