package com.proyecto.web.venta.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
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

import com.proyecto.web.venta.dto.ClienteDto;
import com.proyecto.web.venta.dto.Mensaje;
import com.proyecto.web.venta.model.Reparacion;
import com.proyecto.web.venta.model.STecnico;
import com.proyecto.web.venta.service.ClienteService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@PostMapping
	public ResponseEntity<?> guardar(@Valid @RequestBody ClienteDto clienteDto,BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			return new ResponseEntity(new Mensaje("Error"),HttpStatus.BAD_REQUEST);			
		}
		if(clienteService.existeCliente(clienteDto.getApellido()) && clienteService.traerLista(clienteDto.getApellido(),clienteDto.getNombre())){
			return new ResponseEntity(new Mensaje("Ese cliente ya existe"),HttpStatus.BAD_REQUEST);
		}
		clienteService.guardarCliente(clienteDto);
		return new ResponseEntity(new Mensaje("Cliente creado"),HttpStatus.OK);        
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDto> buscar(@PathVariable("id") int id){
		ClienteDto clienteDto=clienteService.buscarPorId(id);
		if(clienteDto==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(clienteDto);
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteDto>> listar(){
		List<ClienteDto> clienteDtos=clienteService.listarClientes();
		if(clienteDtos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(clienteDtos);
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizar(@PathVariable("id") int id,@RequestBody ClienteDto clienteDto,BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
    		return new ResponseEntity(new Mensaje("error"),HttpStatus.BAD_REQUEST);                	
		}if(!clienteService.existeId(id)) {
    		return new ResponseEntity(new Mensaje("Ese cliente no existe su Id"),HttpStatus.BAD_REQUEST);                				
		}if(clienteService.clienteExistente(clienteDto)) {
    		return new ResponseEntity(new Mensaje("Ese cliente ya existe"),HttpStatus.BAD_REQUEST);                							
		}
		clienteService.actualizar(clienteDto, id);
        return new ResponseEntity(new Mensaje("Aactualizado"),HttpStatus.OK);        

	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminar(@PathVariable ("id") int id){
		if(!clienteService.existeId(id)) {
            return new ResponseEntity(new Mensaje("No existe el cliente"),HttpStatus.NOT_FOUND);
		}
		clienteService.eliminarCliente(id);
	    return new ResponseEntity(new Mensaje("Cliente con Id= "+id+" eliminada"),HttpStatus.OK);        			
		
	}
	
	
	
	
	
	
	@CircuitBreaker(name="tecnicoCB",fallbackMethod="fallBackreturnSTecnico")
	@GetMapping("/buscar/soporte/{idSoporte}")
	public ResponseEntity<STecnico> returnSTecnico(@PathVariable("idSoporte") int idSoporte){
		STecnico sTecnicoDto=clienteService.returnSTecnico(idSoporte);
		return ResponseEntity.ok(sTecnicoDto);
	}

	@CircuitBreaker(name="tecnicoCB",fallbackMethod="fallBackgetSTecnico")
	@GetMapping("/listar/soporte")
	public ResponseEntity<List<STecnico>> getSTecnico(){
		List<STecnico> sTecnicos=clienteService.getSTecnico();
		return ResponseEntity.ok(sTecnicos);
	}
	
	@CircuitBreaker(name="tecnicoCB",fallbackMethod="fallBacksaveSTecnico")	
	@PostMapping("/guardar/soporte/{idCliente}")
	public ResponseEntity<Object> saveSTecnico(@PathVariable("idCliente") int idCliente,@RequestBody STecnico sTecnico){
		Object mensaje=clienteService.guardarSTecnico(idCliente, sTecnico);
		return ResponseEntity.ok(mensaje);
	}
	
	
	@CircuitBreaker(name="tecnicoCB",fallbackMethod="fallBackactualizarSTecnico")
	@PutMapping("/actualizar/soporte/{idSoporte}")
	public ResponseEntity<Object> actualizarSTecnico(@PathVariable("idSoporte") int idSoporte,@RequestBody STecnico sTecnico){
		Object mensaje=clienteService.actualizarSTecnico(idSoporte, sTecnico);
		return ResponseEntity.ok(mensaje);
	}

	@CircuitBreaker(name="tecnicoCB",fallbackMethod="fallBackeliminarSTecnico")
	@DeleteMapping("/eliminar/soporte/{idSoporte}")
	public ResponseEntity<Object> eliminarSTecnico(@PathVariable("idSoporte") int idSoporte){
		Object mensaje=clienteService.eliminarSTecnico(idSoporte);
		return ResponseEntity.ok(mensaje);
	}
	
	@CircuitBreaker(name="tecnicoCB",fallbackMethod="fallBackguardarReaparacion")
	@PostMapping("/guardar/reparaciones")
	public ResponseEntity<Object> guardarReparacion(@RequestBody List<Reparacion> reparacion){
		Object mensaje=clienteService.guardarReparacion(reparacion);
		return ResponseEntity.ok(mensaje);
	}

	@CircuitBreaker(name="tecnicoCB",fallbackMethod="fallBackguardarReaparacion")
	@GetMapping("/listar/reparaciones/{idSoporte}")
	public ResponseEntity<List<Reparacion>> listarReparaciones(@PathVariable("idSoporte") int idSoporte){
		List<Reparacion> reparacions=clienteService.listarReparaciones(idSoporte);
		return ResponseEntity.ok(reparacions);
	}
	
	
	
	
	
	
	public ResponseEntity<STecnico> fallBackreturnSTecnico(@PathVariable("idSoporte") int idSoporte,RuntimeException exception){
		return new ResponseEntity("Sin funcionamiento",HttpStatus.OK);
	}
	
	public ResponseEntity<List<STecnico>> fallBackgetSTecnico(RuntimeException exception){
		return new ResponseEntity("Sin funcionamiento",HttpStatus.OK);		
	}
	
	public ResponseEntity<Object> fallBacksaveSTecnico(@PathVariable("idCliente") int idCliente,@RequestBody STecnico sTecnico,RuntimeException exception){
		return new ResponseEntity("Sin funcionamiento",HttpStatus.OK);				
	}
	
	public ResponseEntity<Object> fallBackactualizarSTecnico(@PathVariable("idSoporte") int idSoporte,@RequestBody STecnico sTecnico,RuntimeException exception){
		return new ResponseEntity("Sin funcionamiento",HttpStatus.OK);						
	}
	
	public ResponseEntity<Object> fallBackeliminarSTecnico(@PathVariable("idSoporte") int idSoporte,RuntimeException exception){
		return new ResponseEntity("Sin funcionamiento",HttpStatus.OK);								
	}
	
	public ResponseEntity<Object> fallBackguardarReaparacion(@RequestBody List<Reparacion> reparacion,RuntimeException exception){
		return new ResponseEntity("Sin funcionamiento",HttpStatus.OK);										
	}

	public ResponseEntity<List<Reparacion>> fallBackguardarReaparacion(@PathVariable("idSoporte") int idSoporte,RuntimeException exception){
		return new ResponseEntity("Sin funcionamiento",HttpStatus.OK);												
	}


}
