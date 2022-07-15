package com.proyecto.web.tecnico.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.web.tecnico.dto.ReparacionDto;
import com.proyecto.web.tecnico.entity.ReparacionEntity;
import com.proyecto.web.tecnico.repository.ReparacionRepository;

@Service
public class ReparacionService {

	@Autowired
	private ReparacionRepository repository;
	
	@Autowired
	private ModelMapper mapper;
	
	private ReparacionEntity mapearEntity(ReparacionDto reparacionDto) {
		ReparacionEntity reparacionEntity=mapper.map(reparacionDto,ReparacionEntity.class);
		return reparacionEntity;
	}
	
	private ReparacionDto mapearDTO(ReparacionEntity reparacionEntity) {
		ReparacionDto reparacionDto=mapper.map(reparacionEntity,ReparacionDto.class);
		return reparacionDto;
	}
	

	private List<ReparacionEntity> mapearListaEntity(List<ReparacionDto> reparacionDtos) {
		List<ReparacionEntity> reparacionEntities=reparacionDtos
													.stream()
													.map(reparacionDto->mapper.map(reparacionDto,ReparacionEntity.class))
													.collect(Collectors.toList());
		return reparacionEntities;
	}
	
	private List<ReparacionDto> mapearListaDTO(List<ReparacionEntity> reparacionEntities){
		List<ReparacionDto> reparacionDtos=reparacionEntities
												.stream()
												.map(reparacionEntity->mapper.map(reparacionEntity,ReparacionDto.class))
												.collect(Collectors.toList());
		return reparacionDtos;
	}
	
	public void guardarReparaciones(List<ReparacionDto> reparacionDtos) {
		List<ReparacionEntity> reparacionEntities=mapearListaEntity(reparacionDtos);
		repository.saveAll(reparacionEntities);
	}
	
	public List<ReparacionDto> listarReparaciones(int id){
		 List<ReparacionEntity> reparacionEntities=repository.findByIdSoporte(id);
		 List<ReparacionDto> reparacionDtos=mapearListaDTO(reparacionEntities);
		 return reparacionDtos;
	}
}
