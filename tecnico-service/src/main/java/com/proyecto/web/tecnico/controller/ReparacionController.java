package com.proyecto.web.tecnico.controller;

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

import com.proyecto.web.tecnico.dto.Mensaje;
import com.proyecto.web.tecnico.dto.ReparacionDto;
import com.proyecto.web.tecnico.service.ReparacionService;

@RestController
@RequestMapping("/reparacion")
public class ReparacionController {

	@Autowired
	private ReparacionService reparacionService;
	
	@PostMapping
	public ResponseEntity<?> guardar(@RequestBody List<ReparacionDto> reparacionDtos){
		reparacionService.guardarReparaciones(reparacionDtos);
		return new ResponseEntity(new Mensaje("Reparacion Creada"),HttpStatus.OK);        
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<ReparacionDto>> listarCarrito(@PathVariable("id") int id){
		List<ReparacionDto> reparacionDtos=reparacionService.listarReparaciones(id);
		if(reparacionDtos.isEmpty()) {return ResponseEntity.noContent().build();}	
		return ResponseEntity.ok(reparacionDtos);

	}
	
}
