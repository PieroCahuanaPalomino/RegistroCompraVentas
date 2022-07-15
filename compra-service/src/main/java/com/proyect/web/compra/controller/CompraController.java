package com.proyect.web.compra.controller;

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

import com.proyect.web.compra.dto.CompraDto;
import com.proyect.web.compra.dto.Mensaje;
import com.proyect.web.compra.dto.ProveedorDto;
import com.proyect.web.compra.service.CompraService;
import com.proyect.web.compra.service.ProveedorService;

@RestController
@RequestMapping("/compra")
public class CompraController {

	@Autowired
	private CompraService compraService;
	@Autowired
	private ProveedorService proveedorService;
	
	@PostMapping
	public ResponseEntity<?> guardar(@Valid @RequestBody CompraDto compraDto,BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			return new ResponseEntity(new Mensaje(bindingResult.toString()),HttpStatus.BAD_REQUEST);  
		}if(compraService.existeSerieComprobante(compraDto.getSerieComprobante())) {
			return new ResponseEntity(new Mensaje("El numero de serie ya existe"),HttpStatus.BAD_REQUEST);	
        }
		compraService.guardarCompra(compraDto);
		return new ResponseEntity(new Mensaje("Compra Creada"),HttpStatus.OK);        
	}
	
	
	@GetMapping
	public ResponseEntity<List<CompraDto>> listar(){
		List<CompraDto> comprasDto=compraService.listarCompra();
		if(comprasDto.isEmpty()) {return ResponseEntity.noContent().build();}
		return ResponseEntity.ok(comprasDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CompraDto> buscar(@PathVariable("id") int id){
		CompraDto compraDto=compraService.buscarPorId(id);
		if(compraDto==null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(compraDto);
		}
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizar(@PathVariable("id") int id,@RequestBody CompraDto compraDto,BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			return new ResponseEntity(new Mensaje(bindingResult.toString()),HttpStatus.BAD_REQUEST);  
		}
		if(!compraService.existeId(id)) {
			return new ResponseEntity(new Mensaje("No existe esa compra"),HttpStatus.NOT_FOUND);		
		}
		if(!proveedorService.existeId(compraDto.getIdProveedor())) {
			return new ResponseEntity(new Mensaje("No existe ese proveedor a actualizar"),HttpStatus.NOT_FOUND);		
		}
		if(compraService.existeSerieComprobante(compraDto.getSerieComprobante()) && compraService.buscarPorSerieComprobante(compraDto.getSerieComprobante()).getIdCompra()!=id) {
			return new ResponseEntity(new Mensaje("Esa serie ya existe"),HttpStatus.NOT_FOUND);						
		}
		compraService.actualizarCompra(id,compraDto);
		return new ResponseEntity(new Mensaje("Compra actualizada"),HttpStatus.OK);								
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminar(@PathVariable ("id") int id){
		CompraDto compraDto=new CompraDto(id);
		if(!compraService.existeId(compraDto.getIdCompra())) {
            return new ResponseEntity(new Mensaje("No existe la compra"),HttpStatus.NOT_FOUND);
		}
        compraService.eliminar(compraDto);
        return new ResponseEntity(new Mensaje("Compra con Id= "+compraDto.getIdCompra()+" eliminada"),HttpStatus.OK);        
	}
}
