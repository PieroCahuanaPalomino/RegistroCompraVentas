package com.proyect.web.compra.dto;

import com.proyect.web.compra.entity.CarritoCompraEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //Constructor lleno
@NoArgsConstructor  //Constructor vacio
public class CarritoCompraDto {

	private int idDetalleCompra;
	
	private int idProducto;
	
	private int idCompra;
	
	private int cantidad;
	
	private float precioSinImpuestos;
	
	private float precioConImpuestos;
	
	private float subTotal;

	public CarritoCompraDto(int idCompra) {
		super();
		this.idCompra = idCompra;
	}
	
	
}
