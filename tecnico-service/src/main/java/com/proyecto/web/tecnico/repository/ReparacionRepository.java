package com.proyecto.web.tecnico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.web.tecnico.entity.ReparacionEntity;

@Repository
public interface ReparacionRepository extends JpaRepository<ReparacionEntity, Integer> {
	public List<ReparacionEntity> findByIdSoporte(int id); 

}
