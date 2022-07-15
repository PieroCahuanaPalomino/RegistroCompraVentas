package com.proyect.web.compra.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.proyect.web.compra.entity.CompraEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //Constructor lleno
@NoArgsConstructor  //Constructor vacio
public class CompraDto {

	private int idCompra;

	private int idProveedor;
	
	//factura-notacredito-boleta venta-nota venta
	private String tipoComprobante;
	
	
	private String serieComprobante;
	
	private String numeroComprobante;
	
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
    //contado-credito
	private String tipoPago;

//determinado 15.25	
	private float impuesto;
	
	private String estado;
	
	private float subtotal;
	
	
	private float totalImpuesto;	
	
	private float totalCompra;

	

	public CompraDto(int idCompra) {
		super();
		this.idCompra = idCompra;
	}



	public CompraDto(int idProveedor, String tipoComprobante, String serieComprobante, String numeroComprobante,
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
