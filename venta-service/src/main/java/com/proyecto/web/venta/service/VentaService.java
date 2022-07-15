package com.proyecto.web.venta.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.proyecto.web.venta.dto.VentaDto;
import com.proyecto.web.venta.entity.VentaEntity;
import com.proyecto.web.venta.repository.VentaRepository;

@Service
public class VentaService {

	@Autowired
	private VentaRepository ventaRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	private VentaEntity mapearEntity(VentaDto ventaDto) {
		VentaEntity ventaEntity=modelMapper.map(ventaDto,VentaEntity.class);
		return ventaEntity;
	}
	
	private VentaDto mapearDTO(VentaEntity ventaEntity) {
		VentaDto ventaDto=modelMapper.map(ventaEntity,VentaDto.class);
		return ventaDto;
	}
	
	private List<VentaDto> mapearListaDto(List<VentaEntity> ventaEntities){
		List<VentaDto> ventaDtos=ventaEntities
									.stream()
									.map(ventaEntity->modelMapper.map(ventaEntity, VentaDto.class))
									.collect(Collectors.toList());
		return ventaDtos;
	}
	
	public void guardarVenta(VentaDto ventaDto){
		VentaEntity ventaEntity=mapearEntity(ventaDto);
		ventaRepository.save(ventaEntity);
	}
	
	public List<VentaDto> listarVentas(){
		List<VentaEntity> ventaEntities=ventaRepository.findAll();
		return mapearListaDto(ventaEntities);
	}
	
	public VentaDto buscarVentaPorId(int id) {
		VentaEntity ventaEntity=ventaRepository.findById(id).orElse(null);
		if(ventaEntity==null) {
			return null;
		}
		return mapearDTO(ventaEntity);
	}
	
	public boolean existeId(int id) {
		return ventaRepository.existsById(id);
	}
	public void actualizarVenta(VentaDto ventaDto,int id) {
		VentaDto antiguoVentaDto=buscarVentaPorId(id);
		VentaEntity antiguoVentaEntity=mapearEntity(antiguoVentaDto);
		
		VentaEntity ventaEntity=mapearEntity(ventaDto);
		
		antiguoVentaEntity.setIdCliente(ventaEntity.getIdCliente());
		antiguoVentaEntity.setNumGuia(ventaEntity.getNumGuia());
		antiguoVentaEntity.setFecha(ventaEntity.getFecha());
		antiguoVentaEntity.setTipoPago(ventaEntity.getTipoPago());
		antiguoVentaEntity.setEstado(ventaEntity.getEstado());
		
		
		ventaEntity=ventaRepository.save(antiguoVentaEntity);
	}
	
	public void eliminarVenta(int id) {
		ventaRepository.deleteById(id);
	}
}
