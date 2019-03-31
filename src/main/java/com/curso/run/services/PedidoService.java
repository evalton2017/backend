package com.curso.run.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.run.model.Pedido;
import com.curso.run.repositories.PedidoRepository;
import com.curso.run.services.Exception.ObjectNotFoundException;


@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Long id){
		Optional<Pedido> cat = repo.findById(id);
		return cat.orElseThrow(()-> new ObjectNotFoundException(
				"Pedido não encontrada Id:"+id+", tipo: "+ Pedido.class.getName()));
	}
	
	
	
}


