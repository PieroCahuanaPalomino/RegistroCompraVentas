package com.proyect.web.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor //Constructor lleno
@NoArgsConstructor  //Constructor vacio
@Entity
@Table(name = "producto")
public class ProductoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idproducto")
	private int idProducto;
	
	@Column(name = "idcategoria")	
	private int idCategoria;
	
	private String nombre;
	private String descripcion;

	@Column(name = "afectacion_impuesto")	
	private String afectacionImpuesto;
	
	@Column(name = "modelo_alterno")
	private String modeloAlterno;
	
	private int stock;
	
	@Column(name = "precio_venta")
	private float precioVenta;
	
	@Column(name = "precio_compra")
	private float precioCompra;

	public ProductoEntity(int idProducto) {
		super();
		this.idProducto = idProducto;
	}

	public ProductoEntity(int idProducto, int idCategoria, String nombre, String descripcion, String afectacionImpuesto,
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
