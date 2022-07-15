package com.proyecto.web.venta.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.proyecto.web.venta.entity.VentaEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //Constructor lleno
@NoArgsConstructor  //Constructor vacio
public class VentaDto {
	
	private int idVenta;

	private int idCliente;

	private String numGuia;
	
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date fecha;

	private String tipoPago;

	private float impuesto;

	private String estado;

	private float subtotal;

	private float totalImpuesto;

	private float totalVenta;

}
