package com.proyect.web.compra.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyect.web.compra.dto.CarritoCompraDto;
import com.proyect.web.compra.dto.CompraDto;
import com.proyect.web.compra.entity.CarritoCompraEntity;
import com.proyect.web.compra.repository.CarritoCompraRepository;

@Service
public class CarritoCompraService {

	@Autowired
	private CarritoCompraRepository carritoCompraRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private CarritoCompraDto mapearDTO(CarritoCompraEntity carritoCompraEntity) {
		CarritoCompraDto carritoCompraDto=modelMapper.map(carritoCompraEntity, CarritoCompraDto.class);
		return carritoCompraDto;
	}
	
	private List<CarritoCompraEntity> mapearListaEntity(List<CarritoCompraDto> carritoCompraDto) {
		List<CarritoCompraEntity> carritoCompraEntity=carritoCompraDto
													.stream()
													.map(carroCompraDto->modelMapper.map(carroCompraDto,CarritoCompraEntity.class))
													.collect(Collectors.toList());
		return carritoCompraEntity;
	}
	
	private List<CarritoCompraDto> mapearListaDTO(List<CarritoCompraEntity> carritoCompraEntities){
		List<CarritoCompraDto> carritoCompraDtos=carritoCompraEntities
												.stream()
												.map(carritoCompraEntity->modelMapper.map(carritoCompraEntity, CarritoCompraDto.class))
												.collect(Collectors.toList());
		return carritoCompraDtos;
	}
	
	public void guardarCarrito(List<CarritoCompraDto> carritoCompraDto) {
		List<CarritoCompraEntity> carritoCompraEntity=mapearListaEntity(carritoCompraDto);
		carritoCompraRepository.saveAll(carritoCompraEntity);
	}
	
	public List<CarritoCompraDto> listarCarrito(int id){
		 List<CarritoCompraEntity> carritoCompraEntities=carritoCompraRepository.findByIdCompra(id);
		 List<CarritoCompraDto> carritoCompraDtos=mapearListaDTO(carritoCompraEntities);
		 return carritoCompraDtos;
	}
}
