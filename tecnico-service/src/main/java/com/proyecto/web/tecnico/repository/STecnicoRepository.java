package com.proyecto.web.tecnico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.web.tecnico.entity.STecnicoEntity;

@Repository
public interface STecnicoRepository extends JpaRepository<STecnicoEntity, Integer>{

}
