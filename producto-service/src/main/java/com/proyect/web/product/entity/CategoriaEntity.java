package com.proyect.web.product.entity;

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
@Table(name = "categoria")
public class CategoriaEntity {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	@Column(name = "idcategoria")
	private int idCategoria;
	
	@Column(name = "nombre")
	private String nombre;

	public CategoriaEntity(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	
	
}
