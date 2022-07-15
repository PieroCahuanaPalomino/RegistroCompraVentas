package com.proyecto.web.tecnico.entity;

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
@Table(name="reparacion")
public class ReparacionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idreparacion")
	private int idReparacion;
	
	@Column(name="idsoporte")
	private int idSoporte;
	
	@Column(name="maquina")
	private String maquina;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name="cobro_subtotal")
	private float cobroSubTotal;
}
