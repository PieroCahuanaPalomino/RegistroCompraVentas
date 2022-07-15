package com.proyecto.web.venta.service;

import java.util.List;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.proyecto.web.venta.dto.ClienteDto;
import com.proyecto.web.venta.dto.Mensaje;
import com.proyecto.web.venta.entity.ClienteEntity;
import com.proyecto.web.venta.feignclients.ReparacionFeignClients;
import com.proyecto.web.venta.feignclients.STecnicoFeignClients;
import com.proyecto.web.venta.model.Reparacion;
import com.proyecto.web.venta.model.STecnico;
import com.proyecto.web.venta.repository.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@Autowired
	private STecnicoFeignClients clients; 
	
	@Autowired
	private ReparacionFeignClients clients2;
	
	private ClienteEntity mapearEntity(ClienteDto clienteDto) {
		ClienteEntity clienteEntity=modelMapper.map(clienteDto, ClienteEntity.class);
		return clienteEntity; 
	}
	
	private ClienteDto mapearDTO(ClienteEntity clienteEntity) {
		ClienteDto clientDto=modelMapper.map(clienteEntity, ClienteDto.class);
		return clientDto; 
	}
	
	private List<ClienteDto> mapearListaDto(List<ClienteEntity> clienteEntities){
		List<ClienteDto> clienteDtos=clienteEntities
									.stream()
									.map(clienteEntity->modelMapper.map(clienteEntity, ClienteDto.class))
									.collect(Collectors.toList());
		return clienteDtos;
	}
	
	public void guardarCliente(ClienteDto clienteDto) {
		ClienteEntity clienteEntity=mapearEntity(clienteDto);
		clienteRepository.save(clienteEntity);
	}
	
	public boolean existeCliente(String apellido) {
		return clienteRepository.existsByApellido(apellido);
		 
	}
	
	public boolean traerLista(String apellido,String nombre) {
		List<ClienteEntity> clienteEntity=clienteRepository.findByApellido(apellido);
		for(ClienteEntity cliente : clienteEntity) {
			if(cliente.getNombre().equals(nombre)) {
				return true;				
			}
		}
		return false;
	}
	
	public ClienteDto buscarPorId(int id) {
		ClienteEntity clienteEntity=clienteRepository.findById(id).orElse(null);
		if(clienteEntity==null) {
			return null;
		}
		ClienteDto clienteDto=mapearDTO(clienteEntity);
		return clienteDto;
	}
	
	public List<ClienteDto> listarClientes(){
		List<ClienteEntity> clienteEntities=clienteRepository.findAll();
		List<ClienteDto> clienteDtos=mapearListaDto(clienteEntities);
		return clienteDtos;
	}
	
	public boolean existeId(int id) {
		return clienteRepository.existsById(id);
	}
	
	public boolean clienteExistente(ClienteDto clienteDto) {
		List<ClienteEntity> clienteEntities=clienteRepository.findByApellido(clienteDto.getApellido());
		for(ClienteEntity cliente:clienteEntities) {
			if(clienteDto.getNombre().equals(cliente.getNombre())) {
				return true;
			}
		}
		return false;
	}
	
	public void actualizar(ClienteDto clienteDto,int id) {
		ClienteDto antiguoClienteDto=buscarPorId(id);
		ClienteEntity antiguoClienteEntity=mapearEntity(antiguoClienteDto);
		
		ClienteEntity clienteEntity=mapearEntity(clienteDto);
		
		antiguoClienteEntity.setNombre(clienteEntity.getNombre());
		antiguoClienteEntity.setApellido(clienteEntity.getApellido());
		antiguoClienteEntity.setDireccion(clienteEntity.getDireccion());
		antiguoClienteEntity.setTelefono(clienteEntity.getTelefono());
		antiguoClienteEntity.setDni(clienteEntity.getDni());
		
		clienteEntity=clienteRepository.save(antiguoClienteEntity);
	}
	
	public void eliminarCliente(int id) {
    	clienteRepository.deleteById(id);
	}
	
	
	
	
	

	
	public STecnico returnSTecnico(int idSoporte) {
		STecnico sTecnico=restTemplate.getForObject("http://tecnico-service/soporte_tecnico/"+idSoporte,STecnico.class);
		return sTecnico;
	}
	
	public List<STecnico> getSTecnico(){
		List<STecnico> sTecnicos=restTemplate.getForObject("http://tecnico-service/soporte_tecnico",List.class);
		return sTecnicos;
	}
	
	
	public Object guardarSTecnico(int idCliente,STecnico sTecnico) {
		sTecnico.setIdCliente(idCliente);
		Object mensaje=clients.guardar(sTecnico);
		return mensaje;
	}
	
	public Object actualizarSTecnico(int idSoporte,STecnico sTecnico) {
		Object mensaje=clients.actualizar(idSoporte, sTecnico);
		return mensaje;
	}
	
	public Object eliminarSTecnico(int idSoporte) {
		Object mensaje=clients.eliminar(idSoporte);
		return mensaje;
	}
	
	
	
	public Object guardarReparacion(List<Reparacion> reparaciones) {
		Object mensaje=clients2.guardar(reparaciones);
		return mensaje;
	}
	
	public List<Reparacion> listarReparaciones(int idSoporte){
		List<Reparacion> reparacions=clients2.listarCarrito(idSoporte);
		return reparacions;
	}
}
