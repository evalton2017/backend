package com.curso.run.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.curso.run.services.MockEmailService;
import com.curso.run.services.validation.DBService;
import com.curso.run.services.validation.EmailService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instanciateDatabase() throws ParseException {
		
		dbService.instantiateTestDatabase();
		return true;
	}
	
	@Bean//fica disponivel como componente no sistema
	public EmailService emailService() {
		return new MockEmailService();
	}

}
