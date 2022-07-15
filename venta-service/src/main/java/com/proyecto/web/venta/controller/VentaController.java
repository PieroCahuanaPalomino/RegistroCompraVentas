package com.proyecto.web.venta.controller;

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

import com.proyecto.web.venta.dto.Mensaje;
import com.proyecto.web.venta.dto.VentaDto;
import com.proyecto.web.venta.service.ClienteService;
import com.proyecto.web.venta.service.VentaService;

@RestController
@RequestMapping("/venta")
public class VentaController {

	@Autowired
	private VentaService ventaService; 
	
	@Autowired
	private ClienteService clienteService;
	
	@PostMapping
	public ResponseEntity<?> guardar(@Valid @RequestBody VentaDto ventaDto,BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			return new ResponseEntity(new Mensaje(bindingResult.toString()),HttpStatus.BAD_REQUEST);  
		}
		ventaService.guardarVenta(ventaDto);
		return new ResponseEntity(new Mensaje("Venta Creada"),HttpStatus.OK);        
	}
	
	@GetMapping
	public ResponseEntity<List<VentaDto>> listar(){
		List<VentaDto> ventaDtos=ventaService.listarVentas();
		if(ventaDtos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(ventaDtos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<VentaDto> buscar(@PathVariable("id") int id){
		VentaDto ventaDto=ventaService.buscarVentaPorId(id);
		if(ventaDto==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(ventaDto);
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizar(@PathVariable("id") int id,@Valid @RequestBody VentaDto ventaDto,BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			return new ResponseEntity(new Mensaje(bindingResult.toString()),HttpStatus.BAD_REQUEST);  
		}
		if(!ventaService.existeId(id)) {
			return new ResponseEntity(new Mensaje("No existe esa venta"),HttpStatus.NOT_FOUND);		
		}
		if(!clienteService.existeId(ventaDto.getIdCliente())) {
			return new ResponseEntity(new Mensaje("No existe ese cliente a actualizar"),HttpStatus.NOT_FOUND);		
		}
		ventaService.actualizarVenta(ventaDto, id);
		return new ResponseEntity(new Mensaje("Venta actualizada"),HttpStatus.OK);								
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminar(@PathVariable("id") int id){
		if(!ventaService.existeId(id)) {
            return new ResponseEntity(new Mensaje("No existe la venta"),HttpStatus.NOT_FOUND);
		}
        ventaService.eliminarVenta(id);
        return new ResponseEntity(new Mensaje("Venta con Id= "+id+" eliminada"),HttpStatus.OK);        
	}
}
