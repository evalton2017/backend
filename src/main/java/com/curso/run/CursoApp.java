package com.curso.run;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.curso.run.model.Categoria;
import com.curso.run.model.Produto;
import com.curso.run.repositories.CategoriaRepository;
import com.curso.run.repositories.ProdutoRepository;

@SpringBootApplication
public class CursoApp implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository catRepo;
	@Autowired
	private ProdutoRepository proRepo;

	public static void main(String[] args) {
		SpringApplication.run(CursoApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null,"Informatica");
		Categoria cat2 = new Categoria(null,"Brinquedos");
		
		Produto p1 = new Produto(null,"Notebook",2000D);
		Produto p2 = new Produto(null,"Impressora",850D);
		Produto p3 = new Produto(null,"Carrinho",35D);
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1));
		p3.getCategorias().addAll(Arrays.asList(cat2));
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2));
		cat2.getProdutos().addAll(Arrays.asList(p3));
		
		
		catRepo.saveAll(Arrays.asList(cat1,cat2));
		proRepo.saveAll(Arrays.asList(p1,p2,p3));
		
	}

}

