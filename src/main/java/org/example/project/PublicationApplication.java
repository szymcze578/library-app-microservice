package org.example.project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.JdbcConnectionDetails;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PublicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(PublicationApplication.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner (JdbcConnectionDetails jdbc){
		return args -> {
			String classDetails = jdbc.getClass().getName();
			String url = jdbc.getJdbcUrl();
			String username = jdbc.getUsername();
			String password = jdbc.getPassword();

			String detail =  classDetails + "\n JDBC url: " + url + "\n Username: " + username + "\n Password: " + password;
			System.out.println(detail);
		};
	}
}
