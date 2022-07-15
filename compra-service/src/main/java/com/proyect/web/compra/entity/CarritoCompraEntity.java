package com.proyect.web.compra.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //Constructor lleno
@NoArgsConstructor  //Constructor vacio
@Entity
@Table(name="detalle_compra")
public class CarritoCompraEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "iddetalle_compra")
	private int idDetalleCompra;
	
	@Column(name = "idproducto")
	private int idProducto;
	
	@Column(name = "idcompra")
	private int idCompra;
	
	@Column(name = "cantidad")
	private int cantidad;
	
	@Column(name ="precio_sin_impuestos")
	private float precioSinImpuestos;
	
	
	@Column(name ="precio_con_impuestos")
	private float precioConImpuestos;
	
	@Column(name = "subtotal")
	private float subTotal;

	public CarritoCompraEntity(int idCompra) {
		super();
		this.idCompra = idCompra;
	}
	
	
}
