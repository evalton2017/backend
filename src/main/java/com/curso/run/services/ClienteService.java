package com.curso.run.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.run.model.Cliente;
import com.curso.run.repositories.ClienteRepository;
import com.curso.run.services.Exception.ObjectNotFoundException;


@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(Long id){
		Optional<Cliente> cat = repo.findById(id);
		return cat.orElseThrow(()-> new ObjectNotFoundException(
				"Cliente não encontrada Id:"+id+", tipo: "+ Cliente.class.getName()));
	}
	
	
	
}


