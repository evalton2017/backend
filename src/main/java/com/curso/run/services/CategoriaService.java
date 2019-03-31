package com.curso.run.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.curso.run.model.Categoria;
import com.curso.run.repositories.CategoriaRepository;
import com.curso.run.services.Exception.DataIntegrityException;
import com.curso.run.services.Exception.ObjectNotFoundException;


@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Long id){
		Optional<Categoria> cat = repo.findById(id);
		return cat.orElseThrow(()-> new ObjectNotFoundException(
				"Categoria não encontrada Id:"+id+", tipo: "+ Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		buscar(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Long id) {
		
		buscar(id);
		try {
			repo.deleteById(id);
		}catch(DataIntegrityViolationException e){
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos");
		}
		
	}
	
	
	
}


