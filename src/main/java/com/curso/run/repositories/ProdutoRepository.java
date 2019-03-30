package com.curso.run.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.curso.run.model.Produto;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
