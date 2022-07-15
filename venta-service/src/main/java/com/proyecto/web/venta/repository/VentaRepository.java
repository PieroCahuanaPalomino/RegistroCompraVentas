package com.proyecto.web.venta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.web.venta.entity.VentaEntity;

@Repository
public interface VentaRepository extends JpaRepository<VentaEntity, Integer>{
}
