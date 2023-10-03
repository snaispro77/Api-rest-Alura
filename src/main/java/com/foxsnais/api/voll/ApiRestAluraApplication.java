package com.foxsnais.api.voll;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiRestAluraApplication {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost/vollmed_api";
		String user = "root";
		String password = "Dorinais_777xd";

		SpringApplication.run(ApiRestAluraApplication.class, args);

	}

}
