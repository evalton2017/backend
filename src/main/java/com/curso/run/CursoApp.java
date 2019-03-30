package com.curso.run;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.curso.run.model.Categoria;
import com.curso.run.model.Cidade;
import com.curso.run.model.Estado;
import com.curso.run.model.Produto;
import com.curso.run.repositories.CategoriaRepository;
import com.curso.run.repositories.CidadeRepository;
import com.curso.run.repositories.EstadoRepository;
import com.curso.run.repositories.ProdutoRepository;

@SpringBootApplication
public class CursoApp implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository catRepo;
	@Autowired
	private ProdutoRepository proRepo;
	@Autowired
	private EstadoRepository estRepo;
	@Autowired
	private CidadeRepository cidRepo;

	public static void main(String[] args) {
		SpringApplication.run(CursoApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//Instancia a categoria
		Categoria cat1 = new Categoria(null,"Informatica");
		Categoria cat2 = new Categoria(null,"Brinquedos");
		
		//Instancia os produtos
		Produto p1 = new Produto(null,"Notebook",2000D);
		Produto p2 = new Produto(null,"Impressora",850D);
		Produto p3 = new Produto(null,"Carrinho",35D);
		
		//associa as categorias aos produtos
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1));
		p3.getCategorias().addAll(Arrays.asList(cat2));
		
		//associa os produtos as categorias
		cat1.getProdutos().addAll(Arrays.asList(p1,p2));
		cat2.getProdutos().addAll(Arrays.asList(p3));
		
		//Salva categoria e produto				
		catRepo.saveAll(Arrays.asList(cat1,cat2));
		proRepo.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade cid1 = new Cidade(null,"Uberlandia", est1);
		Cidade cid2 = new Cidade(null,"Sorocaba", est2);
		Cidade cid3 = new Cidade(null,"São Paulo", est2);
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2,cid3));
		
		estRepo.saveAll(Arrays.asList(est1,est2));
		cidRepo.saveAll(Arrays.asList(cid1,cid2,cid3));
		
		
		
	}

}

