package com.curso.run.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.curso.run.model.Estado;


@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

}
