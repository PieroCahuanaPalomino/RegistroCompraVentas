package com.proyecto.web.venta.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //Constructor lleno
@NoArgsConstructor  //Constructor vacio
public class Reparacion {
	private int idSoporte;
	private String maquina;
	private String descripcion;
	private float cobroSubTotal;
}
