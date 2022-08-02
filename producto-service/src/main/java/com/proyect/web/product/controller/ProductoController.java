package com.proyect.web.product.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyect.web.product.dto.Mensaje;
import com.proyect.web.product.dto.ProductoDto;
import com.proyect.web.product.service.ProductoService;


@RestController
@RequestMapping("/producto")
public class ProductoController {

	
	@Autowired
	private ProductoService productoService;
	
	//se recepcionan por el DTO todo los datos
	@PostMapping
	public ResponseEntity<ProductoDto> guardar(@RequestBody ProductoDto productoDto){
		if(StringUtils.isBlank(productoDto.getNombre())) {
			return new ResponseEntity(new Mensaje("El nombre del producto es obligatorio"),HttpStatus.BAD_REQUEST);
		}
		if(productoService.existeNombre(productoDto.getNombre())) {
			return new ResponseEntity(new Mensaje("El nombre del producto ya existe"),HttpStatus.BAD_REQUEST);
		}
		if(StringUtils.isBlank(productoDto.getAfectacionImpuesto())) {
			return new ResponseEntity(new Mensaje("La afectacion de impuesto es obligatorio"),HttpStatus.BAD_REQUEST);
		}
		if(productoDto.getPrecioCompra()<=0) {
			return new ResponseEntity(new Mensaje("El precio de compra debe ser mayor a 0"),HttpStatus.BAD_REQUEST);
		}
		if(productoDto.getPrecioVenta()<=0) {
			return new ResponseEntity(new Mensaje("El precio de venta debe ser mayor a 0"),HttpStatus.BAD_REQUEST);
		}
		ProductoDto nuevoProductoDto= productoService.guardarProducto(productoDto);
		return ResponseEntity.ok(nuevoProductoDto);
	}
	
	
	//se enviaran por el DTO todos los datos
	@GetMapping("listarProductos")
	@RolesAllowed("backend-admin")
	public ResponseEntity <List<ProductoDto>> listar(){
		List<ProductoDto> productosDto=productoService.listaProducto();
		if(productosDto.isEmpty()) {return ResponseEntity.noContent().build();}
        return ResponseEntity.ok(productosDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductoDto> buscar(@PathVariable("id") int id){
		ProductoDto productoDto=productoService.buscarPorId(id);
		if(productoDto==null) {return ResponseEntity.notFound().build();}
		else {
			return ResponseEntity.ok(productoDto);			
		}
	}
	
	
	//se recepcionan por el DTO todo los datos
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<ProductoDto> actualizar(@PathVariable("id") int id,@RequestBody ProductoDto productoDto){
		if(StringUtils.isBlank(productoDto.getNombre())) {
			return new ResponseEntity(new Mensaje("El nombre es obligatorio"),HttpStatus.BAD_REQUEST);			
		}
		if(!productoService.existeId(id)) {
			return new ResponseEntity(new Mensaje("No existe ese producto"),HttpStatus.NOT_FOUND);
		}
		if(productoService.existeNombre(productoDto.getNombre()) && productoService.buscarPorNombre(productoDto.getNombre()).getIdProducto()!=id) {
			return new ResponseEntity(new Mensaje("Ese nombre ya existe"),HttpStatus.NOT_FOUND);			
		}
		if(productoDto.getPrecioCompra()<=0) {
			return new ResponseEntity(new Mensaje("El precio de compra debe ser mayor a 0"),HttpStatus.BAD_REQUEST);
		}
		if(productoDto.getPrecioVenta()<=0) {
			return new ResponseEntity(new Mensaje("El precio de venta debe ser mayor a 0"),HttpStatus.BAD_REQUEST);
		}
		
		ProductoDto actualizarProductoDto=productoService.actualizar(id, productoDto);
		return ResponseEntity.ok(actualizarProductoDto);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminar(@PathVariable ("id") int id){
		ProductoDto productoDto=new ProductoDto(id);
		if(!productoService.existeId(productoDto.getIdProducto())) {
            return new ResponseEntity(new Mensaje("No existe la categoria"),HttpStatus.NOT_FOUND);
		}
        productoService.eliminar(productoDto);
        return new ResponseEntity(new Mensaje("Categoria con Id= "+productoDto.getIdProducto()+" eliminada"),HttpStatus.OK);        
	}
	
}
