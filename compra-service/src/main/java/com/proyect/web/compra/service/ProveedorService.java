package com.proyect.web.compra.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyect.web.compra.dto.ProveedorDto;
import com.proyect.web.compra.entity.ProveedorEntity;
import com.proyect.web.compra.repository.ProveedorRepository;


@Service
public class ProveedorService {
	
	@Autowired
	private ProveedorRepository proveedorRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private ProveedorDto mapearDTO(ProveedorEntity proveedorEntity){
		ProveedorDto proveedorDto=modelMapper.map(proveedorEntity,ProveedorDto.class);
		return proveedorDto;
	}
	
	private ProveedorEntity mapearEntity(ProveedorDto proveedorDto){
		ProveedorEntity proveedorEntity=modelMapper.map(proveedorDto,ProveedorEntity.class);
		return proveedorEntity;
	}
	
	public void guardarProveedor(ProveedorDto proveedorDto) {
		ProveedorEntity proveedorEntity=mapearEntity(proveedorDto);
		proveedorRepository.save(proveedorEntity);
	}
	
	public List<ProveedorDto> listaProveedor(){
		List<ProveedorEntity> proveedoresEntity=proveedorRepository.findAll();
		List<ProveedorDto> proveedoresDto=proveedoresEntity
											.stream()
											.map(proveedorEntity->modelMapper.map(proveedorEntity,ProveedorDto.class))
											.collect(Collectors.toList());
		return proveedoresDto;
	}
	
	public ProveedorDto buscarPorId(int id){
		ProveedorEntity proveedorEntity=proveedorRepository.findById(id).orElse(null);
		if(proveedorEntity==null) {
			return null;
		}
		ProveedorDto proveedorDto=mapearDTO(proveedorEntity);
		return proveedorDto;
	}
	
	public boolean existeNombre(String nombre) {
		return proveedorRepository.existsByNombreEmpresa(nombre);
	}
	
	public boolean existeId(int id) {
		return proveedorRepository.existsById(id);
	}
	
	public ProveedorDto buscarPorNombre(String nombre) {
		ProveedorEntity proveedorEntity=proveedorRepository.findByNombreEmpresa(nombre);
		ProveedorDto proveedorDto=mapearDTO(proveedorEntity);
		return proveedorDto;
	}
	public void actualizar(int id,ProveedorDto proveedorDto) {
		ProveedorDto antiguoProveedorDto=buscarPorId(id);
		ProveedorEntity antiguoProveedorEntity=mapearEntity(antiguoProveedorDto);
		
		ProveedorEntity proveedorEntity=mapearEntity(proveedorDto);
		
		antiguoProveedorEntity.setNombreEncargado(proveedorEntity.getNombreEncargado());
		antiguoProveedorEntity.setNombreEmpresa(proveedorEntity.getNombreEmpresa());
		antiguoProveedorEntity.setTelefono(proveedorEntity.getTelefono());
		antiguoProveedorEntity.setEmail(proveedorEntity.getEmail());
		antiguoProveedorEntity.setRuc(proveedorEntity.getRuc());
		
		proveedorEntity=proveedorRepository.save(antiguoProveedorEntity);
		
	}
	
    public void eliminar(ProveedorDto proveedorDto) {
    	ProveedorEntity proveedorEntity=mapearEntity(proveedorDto);
    	proveedorRepository.deleteById(proveedorEntity.getIdProveedor());
    }
}
