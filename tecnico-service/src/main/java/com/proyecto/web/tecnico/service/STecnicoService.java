package com.proyecto.web.tecnico.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.web.tecnico.dto.STecnicoDto;
import com.proyecto.web.tecnico.entity.STecnicoEntity;
import com.proyecto.web.tecnico.repository.STecnicoRepository;

@Service
public class STecnicoService {

	@Autowired
	private STecnicoRepository repository;
	
	@Autowired
	private ModelMapper mapper;
	
	private STecnicoEntity mapearEntity(STecnicoDto tecnicoDto) {
		STecnicoEntity tecnicoEntity=mapper.map(tecnicoDto,STecnicoEntity.class);
		return tecnicoEntity;
	}
	
	private STecnicoDto mapearDTO(STecnicoEntity tecnicoEntity) {
		STecnicoDto tecnicoDto=mapper.map(tecnicoEntity,STecnicoDto.class);
		return tecnicoDto;
	}
	
	private List<STecnicoDto> mapearListaDto(List<STecnicoEntity> tecnicoEntities){
		List<STecnicoDto> tecnicoDtos=tecnicoEntities
									.stream()
									.map(tecnicoEntity->mapper.map(tecnicoEntity, STecnicoDto.class))
									.collect(Collectors.toList());
		return tecnicoDtos;
	}
	
	public void guardarSTecnico(STecnicoDto tecnicoDto){
		STecnicoEntity tecnicoEntity=mapearEntity(tecnicoDto);
		repository.save(tecnicoEntity);
	}
	
	public List<STecnicoDto> listarSoporteTecnico(){
		List<STecnicoEntity> sTecnicoEntities=repository.findAll();
		return mapearListaDto(sTecnicoEntities);
	}
	
	public STecnicoDto buscarSoportePorId(int id) {
		STecnicoEntity tecnicoEntity=repository.findById(id).orElse(null);
		if(tecnicoEntity==null) {
			return null;
		}
		return mapearDTO(tecnicoEntity);
	}
	
	
	public boolean existeId(int id) {
		return repository.existsById(id);
	}
	public void actualizarSoporte(STecnicoDto sTecnicoDto,int id) {
		STecnicoDto antiguoSTecnicoDto=buscarSoportePorId(id);
		STecnicoEntity antiguoSTecnicoEntity=mapearEntity(antiguoSTecnicoDto);
		
		STecnicoEntity sTecnicoEntity=mapearEntity(sTecnicoDto);
		
		antiguoSTecnicoEntity.setIdCliente(sTecnicoEntity.getIdCliente());
		antiguoSTecnicoEntity.setNumGuia(sTecnicoEntity.getNumGuia());
		
		sTecnicoEntity=repository.save(antiguoSTecnicoEntity);
	}
	
	public void eliminarSTecnico(int id) {
		repository.deleteById(id);
	}
}
