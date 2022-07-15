package com.proyect.web.compra.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyect.web.compra.dto.CompraDto;
import com.proyect.web.compra.dto.ProveedorDto;
import com.proyect.web.compra.entity.CompraEntity;
import com.proyect.web.compra.entity.ProveedorEntity;
import com.proyect.web.compra.repository.CompraRepository;

@Service
public class CompraService {

	@Autowired
	private CompraRepository compraRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	private CompraDto mapearDTO(CompraEntity compraEntity){
		CompraDto compraDto=modelMapper.map(compraEntity,CompraDto.class);
		return compraDto;
	}
	
	private CompraEntity mapearEntity(CompraDto compraDto){
		CompraEntity compraEntity=modelMapper.map(compraDto,CompraEntity.class);
		return compraEntity;
	}
	
	public void guardarCompra(CompraDto compraDto) {
		CompraEntity compraEntity=mapearEntity(compraDto);
		compraRepository.save(compraEntity);
	}
	
	public List<CompraDto> listarCompra(){
		List<CompraEntity> comprasEntity=compraRepository.findAll();
		List<CompraDto> comprasDto=comprasEntity
											.stream()
											.map(compraEntity->modelMapper.map(compraEntity,CompraDto.class))
											.collect(Collectors.toList());
		return comprasDto;
	}
	
	public CompraDto buscarPorId(int id){
		CompraEntity compraEntity=compraRepository.findById(id).orElse(null);
		if(compraEntity==null) {
			return null;
		}
		CompraDto compraDto=mapearDTO(compraEntity);
		return compraDto;
	}
	
	public boolean existeSerieComprobante(String serieComprobante) {
		return compraRepository.existsBySerieComprobante(serieComprobante);
	}
	
	public boolean existeId(int id) {
		return compraRepository.existsById(id);
	}
	
	public CompraDto buscarPorSerieComprobante(String serieComprobante) {
		CompraEntity compraEntity=compraRepository.findBySerieComprobante(serieComprobante);
		CompraDto compraDto=mapearDTO(compraEntity);
		return compraDto;
	}
	
	public void actualizarCompra(int id,CompraDto compraDto) {
		CompraDto antiguoCompraDto=buscarPorId(id);
		CompraEntity antiguoCompraEntity=mapearEntity(antiguoCompraDto);
		
		CompraEntity compraEntity=mapearEntity(compraDto);
		
		antiguoCompraEntity.setIdProveedor(compraEntity.getIdProveedor());
		antiguoCompraEntity.setTipoComprobante(compraEntity.getTipoComprobante());
		antiguoCompraEntity.setSerieComprobante(compraEntity.getSerieComprobante());
		antiguoCompraEntity.setNumeroComprobante(compraEntity.getNumeroComprobante());
		antiguoCompraEntity.setFecha(compraEntity.getFecha());
		antiguoCompraEntity.setTipoPago(compraEntity.getTipoPago());
		antiguoCompraEntity.setEstado(compraEntity.getEstado());
		
		
		compraEntity=compraRepository.save(antiguoCompraEntity);
	}
	
    public void eliminar(CompraDto compraDto) {
    	CompraEntity compraEntity=mapearEntity(compraDto);
    	compraRepository.deleteById(compraEntity.getIdCompra());
    }
}
