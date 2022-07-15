package com.proyecto.web.venta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.web.venta.dto.CarritoVentaDto;
import com.proyecto.web.venta.dto.Mensaje;
import com.proyecto.web.venta.service.CarritoVentaService;


@RestController
@RequestMapping("/carrito_venta")
public class CarritoVentaController {
	@Autowired
	private CarritoVentaService carritoVentaService;
	
	@PostMapping
	public ResponseEntity<?> guardar(@RequestBody List<CarritoVentaDto> carritoVentaDto){
		carritoVentaService.guardarCarrito(carritoVentaDto);
		return new ResponseEntity(new Mensaje("Carrito Creado"),HttpStatus.OK);        
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<CarritoVentaDto>> listarCarrito(@PathVariable("id") int id){
		List<CarritoVentaDto> carritosVentaDtos=carritoVentaService.listarCarrito(id);
		if(carritosVentaDtos.isEmpty()) {return ResponseEntity.noContent().build();}	
		return ResponseEntity.ok(carritosVentaDtos);

	}
}
