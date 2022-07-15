package com.proyecto.web.tecnico.entity;

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
@Table(name="soporte_tecnico")
public class STecnicoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idsoporte")
	private int idSoporte;
	
	@Column(name="idcliente")
	private int idCliente;
	
	@Column(name="numguia")
	private String numGuia;
	
	@Column(name="fecha")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@Column(name="cobro_total")
	private float cobroTotal;
	
	@Column(name="ingreso_caja_total")
	private float ingresoCajaTotal;
	
	@Column(name="ingreso_soporte")
	private float ingresoSoporte;
	
}
