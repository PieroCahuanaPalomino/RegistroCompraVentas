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

import com.proyect.web.compra.dto.Mensaje;
import com.proyect.web.compra.dto.ProveedorDto;
import com.proyect.web.compra.entity.ProveedorEntity;
import com.proyect.web.compra.service.ProveedorService;

@RestController
@RequestMapping("/proveedor")
public class ProveedorController {

	@Autowired
	private ProveedorService proveedorService;
	
	@PostMapping
	public ResponseEntity<?> guardar(@Valid @RequestBody ProveedorDto proveedorDto,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
    		return new ResponseEntity(new Mensaje("error"),HttpStatus.BAD_REQUEST);                	
        }if(proveedorService.existeNombre(proveedorDto.getNombreEmpresa())) {
			return new ResponseEntity(new Mensaje("El nombre de la empresa ya existe"),HttpStatus.BAD_REQUEST);	
        }
        proveedorService.guardarProveedor(proveedorDto);
		return new ResponseEntity(new Mensaje("Proveedor Creado"),HttpStatus.OK);        
	}
	
	@GetMapping
	public ResponseEntity<List<ProveedorDto>> listar(){
		List<ProveedorDto> proveedoresDto=proveedorService.listaProveedor();
		if(proveedoresDto.isEmpty()) {return ResponseEntity.noContent().build();}
		return ResponseEntity.ok(proveedoresDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProveedorDto> buscar(@PathVariable("id") int id){
		ProveedorDto proveedorDto=proveedorService.buscarPorId(id);
		if(proveedorDto==null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(proveedorDto);
		}
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizar(@PathVariable("id") int id,@RequestBody ProveedorDto proveedorDto,BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
    		return new ResponseEntity(new Mensaje("error"),HttpStatus.BAD_REQUEST);                	
		}
		if(!proveedorService.existeId(id)) {
			return new ResponseEntity(new Mensaje("No existe ese proveedor"),HttpStatus.NOT_FOUND);		
		}
		if(proveedorService.existeNombre(proveedorDto.getNombreEmpresa()) && proveedorService.buscarPorNombre(proveedorDto.getNombreEmpresa()).getIdproveedor()!=id) {
			return new ResponseEntity(new Mensaje("Ese nombre de empresa ya existe"),HttpStatus.NOT_FOUND);						
		}
		proveedorService.actualizar(id, proveedorDto);
        return new ResponseEntity(new Mensaje("Proveedor con Id= "+id+" actualizado"),HttpStatus.OK);        
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminar(@PathVariable ("id") int id){
		ProveedorDto proveedorDto=new ProveedorDto(id);
		if(!proveedorService.existeId(proveedorDto.getIdproveedor())) {
            return new ResponseEntity(new Mensaje("No existe el proveedor"),HttpStatus.NOT_FOUND);
		}
        proveedorService.eliminar(proveedorDto);
        return new ResponseEntity(new Mensaje("Categoria con Id= "+proveedorDto.getIdproveedor()+" eliminada"),HttpStatus.OK);        
	}
}
