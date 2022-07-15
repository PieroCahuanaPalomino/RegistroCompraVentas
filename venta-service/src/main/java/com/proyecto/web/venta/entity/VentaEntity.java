package com.proyecto.web.venta.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //Constructor lleno
@NoArgsConstructor  //Constructor vacio
@Entity
@Table(name="venta")
public class VentaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idventa")
	private int idVenta;

	@Column(name="idcliente")
	private int idCliente;
	
	@Column(name="num_guia")
	private String numGuia;
	
	@Column(name = "fecha")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@Column(name = "tipo_pago")
	private String tipoPago;
	
	@Column(name = "impuesto")	
	private float impuesto;
	
	@Column(name = "estado")	
	private String estado;
	
	@Column(name = "sub_total")	
	private float subtotal;
	
	@Column(name = "total_impuesto")	
	private float totalImpuesto;
	
	@Column(name = "total_venta")	
	private float totalVenta;
}
