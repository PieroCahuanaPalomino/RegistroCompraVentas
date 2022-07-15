package com.proyect.web.compra.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.proyect.web.compra.entity.ProveedorEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //Constructor lleno
@NoArgsConstructor  //Constructor vacio
public class ProveedorDto {
	
	private int idproveedor;
	
	@NotNull
	@NotBlank
	private String nombreEncargado;

	@NotNull
	@NotBlank
	private String nombreEmpresa;

	@Length(min=9,max = 9)
	private String telefono;

	@Email(message="Error de formato email")
	private String email;

	@Size(max = 11)
	private String ruc;

	public ProveedorDto(int idproveedor) {
		super();
		this.idproveedor = idproveedor;
	}
	
	

}
