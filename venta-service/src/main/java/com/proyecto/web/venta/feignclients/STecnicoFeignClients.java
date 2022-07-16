package com.proyecto.web.venta.feignclients;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.web.venta.model.STecnico;

@FeignClient(name="tecnico-service")
@RequestMapping("/soporte_tecnico")
public interface STecnicoFeignClients {
	
	@GetMapping("/{id}")
	public STecnico buscar(@PathVariable("id") int id);
	
	@PostMapping
	public Object guardar(@Valid @RequestBody STecnico tecnico);
	
	@PutMapping("/actualizar/{id}")
	public Object actualizar(@PathVariable("id") int id,@Valid @RequestBody STecnico tecnico);
	
	@DeleteMapping("/eliminar/{id}")
	public Object eliminar(@PathVariable("id") int id);


	
}
