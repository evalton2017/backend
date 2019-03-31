package com.curso.run.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.curso.run.dto.ClienteDTO;
import com.curso.run.model.Cliente;
import com.curso.run.repositories.ClienteRepository;
import com.curso.run.services.Exception.DataIntegrityException;
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
	
	
	public Cliente insert(Cliente obj) {
		return repo.save(obj);
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = buscar(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Long id) {
		
		buscar(id);
		try {
			repo.deleteById(id);
		}catch(DataIntegrityViolationException e){
			throw new DataIntegrityException("Não é possivel excluir um cliente que possui pedidos");
		}
		
	}
	
	public List<Cliente> findAll(){
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(),objDTO.getNome(),objDTO.getEmail(),null,null);
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	

	
	
	
}


