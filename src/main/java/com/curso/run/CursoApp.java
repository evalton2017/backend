package com.curso.run;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.curso.run.model.Categoria;
import com.curso.run.repositories.CategoriaRepository;

@SpringBootApplication
public class CursoApp implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository catRepo;

	public static void main(String[] args) {
		SpringApplication.run(CursoApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null,"Informatica");
		Categoria cat2 = new Categoria(null,"Brinquedos");
		
		catRepo.saveAll(Arrays.asList(cat1,cat2));
		
	}

}

