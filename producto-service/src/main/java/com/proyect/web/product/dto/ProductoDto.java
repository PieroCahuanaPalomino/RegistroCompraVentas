package com.proyect.web.product.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.proyect.web.product.entity.ProductoEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor //Constructor lleno
@NoArgsConstructor  //Constructor vacio
public class ProductoDto {

	private int idProducto;
	
	private int idCategoria;
	
	private String nombre;
	
	private String descripcion;

	//gravable o exonerada
	private String afectacionImpuesto;

	private String modeloAlterno;

	private int stock;

	private float precioVenta;

	private float precioCompra;

	public ProductoDto(int idProducto) {
		super();
		this.idProducto = idProducto;
	}

	public ProductoDto(int idProducto, int idCategoria, String nombre, String descripcion, String afectacionImpuesto,
			String modeloAlterno, float precioVenta, float precioCompra) {
		super();
		this.idProducto = idProducto;
		this.idCategoria = idCategoria;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.afectacionImpuesto = afectacionImpuesto;
		this.modeloAlterno = modeloAlterno;
		this.precioVenta = precioVenta;
		this.precioCompra = precioCompra;
	}
	
	
	
	
}
