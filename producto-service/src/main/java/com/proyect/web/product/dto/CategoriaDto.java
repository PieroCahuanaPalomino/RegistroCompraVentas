package com.proyect.web.product.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //Constructor lleno
@NoArgsConstructor  //Constructor vacio
public class CategoriaDto {

	private int idCategoria;

	@NotBlank
	private String nombre;

	public CategoriaDto(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	
	

}
