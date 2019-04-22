package com.curso.run;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.curso.run.services.S3Service;

@SpringBootApplication
public class CursoApp implements CommandLineRunner {
	
	@Autowired
	private S3Service s3;


	public static void main(String[] args) {
		SpringApplication.run(CursoApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		s3.uploadFile("D:\\FOTOS\\DUKE\\duke.jpg");
	}

}

