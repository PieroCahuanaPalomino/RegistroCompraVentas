package com.proyect.web.compra.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //Constructor lleno
@NoArgsConstructor  //Constructor vacio
@Entity
@Table(name = "compra")
public class CompraEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idcompra")
	private int idCompra;

	@Column(name="idproveedor")
	private int idProveedor;
	
	@Column(name="tipo_comprobante")
	private String tipoComprobante;
	
	@Column(name="serie_comprobante")
	private String serieComprobante;
	
	@Column(name="num_comprobante")
	private String numeroComprobante;
	
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
	
	@Column(name = "total_compra")	
	private float totalCompra;

	public CompraEntity(int idCompra) {
		super();
		this.idCompra = idCompra;
	}

	public CompraEntity(int idProveedor, String tipoComprobante, String serieComprobante, String numeroComprobante,
			Date fecha, String tipoPago, float impuesto, String estado, float subtotal, float totalImpuesto,
			float totalCompra) {
		super();
		this.idProveedor = idProveedor;
		this.tipoComprobante = tipoComprobante;
		this.serieComprobante = serieComprobante;
		this.numeroComprobante = numeroComprobante;
		this.fecha = fecha;
		this.tipoPago = tipoPago;
		this.impuesto = impuesto;
		this.estado = estado;
		this.subtotal = subtotal;
		this.totalImpuesto = totalImpuesto;
		this.totalCompra = totalCompra;
	}

	
}
