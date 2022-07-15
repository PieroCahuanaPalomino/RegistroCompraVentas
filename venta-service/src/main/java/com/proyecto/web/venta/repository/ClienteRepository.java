package com.proyecto.web.venta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.web.venta.entity.ClienteEntity;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity,Integer>{
	boolean existsByApellido(String apellido);
	
	List<ClienteEntity> findByApellido(String apellido);
}
