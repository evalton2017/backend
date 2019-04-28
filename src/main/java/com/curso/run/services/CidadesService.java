package com.curso.run.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.run.model.Cidade;
import com.curso.run.repositories.CidadeRepository;

@Service
public class CidadesService {

	@Autowired
	private CidadeRepository repo;
	
	public List<Cidade> findByEstado(Long estadoId){
		return repo.findCidades(estadoId);
	}
}
