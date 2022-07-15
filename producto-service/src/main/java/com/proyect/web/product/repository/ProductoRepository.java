package com.proyect.web.product.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyect.web.product.dto.ProductoDto;
import com.proyect.web.product.entity.ProductoEntity;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Integer>{
	ProductoEntity findByNombre(String nombre);
	boolean existsByNombre(String nombre);
}
