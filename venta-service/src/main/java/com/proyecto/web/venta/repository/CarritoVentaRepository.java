package com.proyecto.web.venta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.web.venta.entity.CarritoVentaEntity;

public interface CarritoVentaRepository extends JpaRepository<CarritoVentaEntity, Integer>{
	public List<CarritoVentaEntity> findByIdVenta(int id); 

}
