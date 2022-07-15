package com.proyecto.web.tecnico.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.web.tecnico.dto.Mensaje;
import com.proyecto.web.tecnico.dto.STecnicoDto;
import com.proyecto.web.tecnico.service.STecnicoService;

@RestController
@RequestMapping("/soporte_tecnico")
public class STecnicoController {
	
	@Autowired
	private STecnicoService tecnicoService;
	
	@PostMapping
	public ResponseEntity<?> guardar(@Valid @RequestBody STecnicoDto tecnicoDto,BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			return new ResponseEntity(new Mensaje(bindingResult.toString()),HttpStatus.BAD_REQUEST);  
		}
		tecnicoService.guardarSTecnico(tecnicoDto);
		return new ResponseEntity(new Mensaje("Arreglo Creada"),HttpStatus.OK);        
	}
	
	@GetMapping
	public ResponseEntity<List<STecnicoDto>> listar(){
		List<STecnicoDto> sTecnicoDtos=tecnicoService.listarSoporteTecnico();
		if(sTecnicoDtos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(sTecnicoDtos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<STecnicoDto> buscar(@PathVariable("id") int id){
		STecnicoDto sTecnicoDto=tecnicoService.buscarSoportePorId(id);
		if(sTecnicoDto==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(sTecnicoDto);
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizar(@PathVariable("id") int id,@Valid @RequestBody STecnicoDto tecnicoDto,BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			return new ResponseEntity(new Mensaje(bindingResult.toString()),HttpStatus.BAD_REQUEST);  
		}
		if(!tecnicoService.existeId(id)) {
			return new ResponseEntity(new Mensaje("No existe ese Soporte"),HttpStatus.NOT_FOUND);		
		}
		tecnicoService.actualizarSoporte(tecnicoDto, id);
		return new ResponseEntity(new Mensaje("Soporte actualizada"),HttpStatus.OK);								
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminar(@PathVariable("id") int id){
		if(!tecnicoService.existeId(id)) {
            return new ResponseEntity(new Mensaje("No existe ese soporte"),HttpStatus.NOT_FOUND);
		}
        tecnicoService.eliminarSTecnico(id);
        return new ResponseEntity(new Mensaje("Soporte con Id= "+id+" eliminada"),HttpStatus.OK);        
	}
}
