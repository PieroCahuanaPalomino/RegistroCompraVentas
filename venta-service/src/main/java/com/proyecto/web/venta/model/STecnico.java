package com.proyecto.web.venta.model;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class STecnico {
	private int idSoporte;
	private int idCliente;
	private String numGuia;
	
	@JsonFormat(pattern="yyyy-MM-dd")
    private Date fecha;
    
	private float cobroTotal;
	private float ingresoCajaTotal;
	private float ingresoSoporte;
}
