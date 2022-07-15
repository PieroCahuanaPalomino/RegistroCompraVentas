package com.proyect.web.product.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
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

import com.proyect.web.product.dto.CategoriaDto;
import com.proyect.web.product.dto.Mensaje;
import com.proyect.web.product.service.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	@PostMapping()
	public ResponseEntity<CategoriaDto> guardar(@RequestBody CategoriaDto categoriaDto){
		if(StringUtils.isBlank(categoriaDto.getNombre())) {
			return new ResponseEntity(new Mensaje("El nombre de la categoria es obligatorio"),HttpStatus.BAD_REQUEST);
		}
		if(categoriaService.existeNombre(categoriaDto.getNombre())) {
			return new ResponseEntity(new Mensaje("El nombre de la categoria ya existe"),HttpStatus.BAD_REQUEST);
		}
		CategoriaDto nuevaCategoriaDto=categoriaService.guardarCategoria(categoriaDto);
		return ResponseEntity.ok(nuevaCategoriaDto); //mensaje producto creado
	}
	
	@GetMapping()
	public ResponseEntity<List<CategoriaDto>> listar(){
		List<CategoriaDto> categoriasDto=categoriaService.listaCategoria();
		if(categoriasDto.isEmpty()) {return ResponseEntity.noContent().build();}
                return ResponseEntity.ok(categoriasDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaDto> buscar(@PathVariable("id") int id){
		CategoriaDto categoriaDto=categoriaService.buscarPorId(id);
		if(categoriaDto==null) {return ResponseEntity.notFound().build();}
		return ResponseEntity.ok(categoriaDto);
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<CategoriaDto> actualizar(@PathVariable("id") int id,@RequestBody CategoriaDto categoriaDto){
		if(StringUtils.isBlank(categoriaDto.getNombre())) {
			return new ResponseEntity(new Mensaje("El campo nombre es obligatorio"),HttpStatus.BAD_REQUEST);			
		}
		if(!categoriaService.existeId(id)) {
			return new ResponseEntity(new Mensaje("No existe esa categoria"),HttpStatus.NOT_FOUND);
		}
		if(categoriaService.existeNombre(categoriaDto.getNombre())) {
			return new ResponseEntity(new Mensaje("Ese nombre ya existe"),HttpStatus.NOT_FOUND);			
		}
		CategoriaDto actualizarCategoriaDto=categoriaService.actualizar(id, categoriaDto);
		return ResponseEntity.ok(actualizarCategoriaDto);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminar(@PathVariable ("id") int id){
		CategoriaDto categoriaDto=new CategoriaDto(id);
		if(!categoriaService.existeId(categoriaDto.getIdCategoria())) {
            return new ResponseEntity(new Mensaje("No existe la categoria"),HttpStatus.NOT_FOUND);
		}
        categoriaService.eliminar(categoriaDto);
        return new ResponseEntity(new Mensaje("Categoria con Id= "+categoriaDto.getIdCategoria()+" eliminada"),HttpStatus.OK);        
	}
}
