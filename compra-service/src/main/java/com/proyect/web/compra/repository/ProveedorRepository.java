package com.proyect.web.compra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyect.web.compra.entity.ProveedorEntity;

@Repository
public interface ProveedorRepository extends JpaRepository<ProveedorEntity, Integer>{
	ProveedorEntity findByNombreEmpresa(String nombre);
	boolean existsByNombreEmpresa(String nombre);
}
