package com.proyecto.web.venta.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //Constructor lleno
@NoArgsConstructor  //Constructor vacio
public class CarritoVentaDto {
	private int idDetalleVenta;

	private int idProducto;

	private int idVenta;

	private int cantidad;

	private float precio;

	private float subTotal;

}
