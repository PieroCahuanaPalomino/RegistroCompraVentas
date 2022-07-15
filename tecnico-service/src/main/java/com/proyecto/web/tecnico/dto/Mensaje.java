package com.proyecto.web.tecnico.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //Constructor lleno
@NoArgsConstructor  //Constructor vacio
public class Mensaje {
	private String mensaje;
}
