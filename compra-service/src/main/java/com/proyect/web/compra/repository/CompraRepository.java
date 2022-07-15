package com.proyect.web.compra.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyect.web.compra.entity.CompraEntity;

@Repository
public interface CompraRepository  extends JpaRepository<CompraEntity, Integer>{
	CompraEntity findBySerieComprobante(String serieComprobante);
	boolean existsBySerieComprobante(String serieComprobante);
}
