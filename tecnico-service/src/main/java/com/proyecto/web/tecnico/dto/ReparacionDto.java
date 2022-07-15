package com.proyecto.web.tecnico.dto;

import com.proyecto.web.tecnico.entity.ReparacionEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //Constructor lleno
@NoArgsConstructor  //Constructor vacio
public class ReparacionDto {
	private int idReparacion;
	private int idSoporte;
	private String maquina;
	private String descripcion;
	private float cobroSubTotal;

}
