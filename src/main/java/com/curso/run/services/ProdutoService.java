package com.curso.run.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.curso.run.model.Categoria;
import com.curso.run.model.Produto;
import com.curso.run.repositories.CategoriaRepository;
import com.curso.run.repositories.ProdutoRepository;
import com.curso.run.services.Exception.ObjectNotFoundException;


@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository repoCat;
	
	public Produto buscar(Long id){
		Optional<Produto> cat = repo.findById(id);
		return cat.orElseThrow(()-> new ObjectNotFoundException(
				"Pedido não encontrada Id:"+id+", tipo: "+ Produto.class.getName()));
	}
	
	public Page<Produto> search(String nome, List<Long> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = repoCat.findAllById(ids);
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome,categorias, pageRequest);
	
	}
	
	
	
}


