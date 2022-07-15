package com.proyecto.web.venta.dto;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //Constructor lleno
@NoArgsConstructor  //Constructor vacio
public class ClienteDto {
	private int idCliente;
	
	@NotBlank
	private String nombre;
	
	@NotBlank
	private String apellido;
	
	private String direccion;
	
	private String telefono;
	
	@Size(min = 0,max = 8,message = "dni debe tener 9 caracteres")
	private String dni;
	
	private String email;
}
