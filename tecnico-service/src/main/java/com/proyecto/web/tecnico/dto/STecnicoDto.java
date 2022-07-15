package com.proyecto.web.tecnico.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.proyecto.web.tecnico.entity.STecnicoEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //Constructor lleno
@NoArgsConstructor  //Constructor vacio
public class STecnicoDto {

	private int idSoporte;
	private int idCliente;
	private String numGuia;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date fecha;
	private float cobroTotal;
	private float ingresoCajaTotal;
	private float ingresoSoporte;

}
