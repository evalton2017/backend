package com.curso.run.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.curso.run.model.Categoria;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Categoria> lista() {
		Categoria cat1 = new Categoria(1L,"Informatica");
		Categoria cat2 = new Categoria(2L,"Brinquedos");
		
		List<Categoria> lista= new ArrayList<>();
		lista.add(cat1);
		lista.add(cat2);
		
		return lista;
		
	}

}
