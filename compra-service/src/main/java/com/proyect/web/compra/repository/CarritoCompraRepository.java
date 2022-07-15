package com.proyect.web.compra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyect.web.compra.entity.CarritoCompraEntity;

public interface CarritoCompraRepository extends JpaRepository<CarritoCompraEntity,Integer> {
	public List<CarritoCompraEntity> findByIdCompra(int id); 
}
