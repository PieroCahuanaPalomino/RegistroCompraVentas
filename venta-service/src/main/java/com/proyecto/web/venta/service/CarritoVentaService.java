package com.proyecto.web.venta.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.proyecto.web.venta.dto.CarritoVentaDto;
import com.proyecto.web.venta.entity.CarritoVentaEntity;
import com.proyecto.web.venta.repository.CarritoVentaRepository;

@Service
public class CarritoVentaService {

	@Autowired
	private CarritoVentaRepository carritoVentaRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	
	private CarritoVentaDto mapearDTO(CarritoVentaEntity carritoVentaEntity) {
		CarritoVentaDto carritoVentaDto=modelMapper.map(carritoVentaEntity, CarritoVentaDto.class);
		return carritoVentaDto;
	}
	
	private List<CarritoVentaEntity> mapearListaEntity(List<CarritoVentaDto> carritoVentaDto) {
		List<CarritoVentaEntity> carritoVentaEntity=carritoVentaDto
													.stream()
													.map(carroVentaDto->modelMapper.map(carroVentaDto,CarritoVentaEntity.class))
													.collect(Collectors.toList());
		return carritoVentaEntity;
	}
	
	private List<CarritoVentaDto> mapearListaDTO(List<CarritoVentaEntity> carritoVentaEntities){
		List<CarritoVentaDto> carritoVentaDtos=carritoVentaEntities
												.stream()
												.map(carritoVentaEntity->modelMapper.map(carritoVentaEntity, CarritoVentaDto.class))
												.collect(Collectors.toList());
		return carritoVentaDtos;
	}
	
	public void guardarCarrito(List<CarritoVentaDto> carritoVentaDto) {
		List<CarritoVentaEntity> carritoVentaEntity=mapearListaEntity(carritoVentaDto);
		carritoVentaRepository.saveAll(carritoVentaEntity);
	}
	
	public List<CarritoVentaDto> listarCarrito(int id){
		 List<CarritoVentaEntity> carritoVentaEntities=carritoVentaRepository.findByIdVenta(id);
		 List<CarritoVentaDto> carritoVentaDtos=mapearListaDTO(carritoVentaEntities);
		 return carritoVentaDtos;
	}
	
	
	
	
	
	
}
