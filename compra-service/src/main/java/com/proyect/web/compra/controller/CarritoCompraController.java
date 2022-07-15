package com.proyect.web.compra.controller;

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

import com.proyect.web.compra.dto.CarritoCompraDto;
import com.proyect.web.compra.dto.Mensaje;
import com.proyect.web.compra.service.CarritoCompraService;

@RestController
@RequestMapping("/carrito_compra")
public class CarritoCompraController {
	
	@Autowired
	private CarritoCompraService carritoCompraService;
	
	@PostMapping
	public ResponseEntity<?> guardar(@RequestBody List<CarritoCompraDto> carritoCompraDto){
		carritoCompraService.guardarCarrito(carritoCompraDto);
		return new ResponseEntity(new Mensaje("Carrito Creado"),HttpStatus.OK);        
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<CarritoCompraDto>> listarCarrito(@PathVariable("id") int id){
		List<CarritoCompraDto> carritosCompraDtos=carritoCompraService.listarCarrito(id);
		if(carritosCompraDtos.isEmpty()) {return ResponseEntity.noContent().build();}	
		return ResponseEntity.ok(carritosCompraDtos);

	}
	
}
