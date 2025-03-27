package com.example.security_ex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class SecurityExApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityExApplication.class, args);
	}

}
