package com.dgmf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
// Optionals
// Because of Spring Boot AutoConfiguration
// @EnableWebSecurity
// Because of Package Structure
/*@EnableJpaRepositories("com.dgmf.repository")
@EntityScan("com.dgmf.entity")*/
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
