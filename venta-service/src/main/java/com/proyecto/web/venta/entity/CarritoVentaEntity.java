package com.proyecto.web.venta.entity;

import java.util.Date;

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
@Table(name = "detalle_venta")
public class CarritoVentaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "iddetalle_venta")
	private int idDetalleVenta;
	
	@Column(name = "idproducto")
	private int idProducto;
	
	@Column(name = "idventa")
	private int idVenta;
	
	@Column(name = "cantidad")
	private int cantidad;
	
	@Column(name = "precio")
	private float precio;
	
	@Column(name = "subtotal")
	private float subTotal;
	
	
}
