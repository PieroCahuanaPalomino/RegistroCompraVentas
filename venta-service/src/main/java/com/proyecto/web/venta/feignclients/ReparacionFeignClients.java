package com.proyecto.web.venta.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.web.venta.model.Reparacion;


@FeignClient(name="tecnico-service")
@RequestMapping("/reparacion")
public interface ReparacionFeignClients {

	@PostMapping
	public Object guardar(@RequestBody List<Reparacion> reparaciones);
	
	@GetMapping("/{id}")
	public List<Reparacion> listarCarrito(@PathVariable("id") int id);
}
