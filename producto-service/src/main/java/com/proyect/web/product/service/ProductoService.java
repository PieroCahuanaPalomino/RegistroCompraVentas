  package com.proyect.web.product.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyect.web.product.dto.CategoriaDto;
import com.proyect.web.product.dto.ProductoDto;
import com.proyect.web.product.entity.CategoriaEntity;
import com.proyect.web.product.entity.ProductoEntity;
import com.proyect.web.product.repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ModelMapper modelMapper;

    //Convierte Entidad a DTO
    private ProductoDto mapearDTO(ProductoEntity productoEntity) {
        ProductoDto productoDto = modelMapper.map(productoEntity, ProductoDto.class);
        return productoDto;
    }

    //Convierte DTO a Entidad
    private ProductoEntity mapearEntidad(ProductoDto productoDto) {
        ProductoEntity productoEntity = modelMapper.map(productoDto, ProductoEntity.class);
        return productoEntity;
    }

    public ProductoDto guardarProducto(ProductoDto productoDto) {
        ProductoEntity productoEntity = mapearEntidad(productoDto);
        ProductoEntity nuevoProductoEntity = productoRepository.save(productoEntity);
        ProductoDto nuevoProductoDto = mapearDTO(nuevoProductoEntity);
        return nuevoProductoDto;
    }
    
    public List<ProductoDto> listaProducto() {
    	List<ProductoEntity> productosEntity=productoRepository.findAll();
    	List<ProductoDto> productosDto=productosEntity
    										.stream()
    										.map(productoEntity->modelMapper.map(productoEntity,ProductoDto.class))
    										.collect(Collectors.toList());
    	return productosDto;
    }
    
    public ProductoDto buscarPorId(int id) {
    	ProductoEntity productoEntity=productoRepository.findById(id).orElse(null);
    	if(productoEntity==null) {
    		return null;
    	}
    	ProductoDto productoDto=mapearDTO(productoEntity);
    	return productoDto;
    }
    
    public boolean existeNombre(String nombre) {
    	return productoRepository.existsByNombre(nombre);
    }
    
    public boolean existeId(int id) {
    	return productoRepository.existsById(id);
    }
    
    public ProductoDto buscarPorNombre(String nombre){
    	ProductoEntity productoEntity=productoRepository.findByNombre(nombre);
    	ProductoDto productoDto=mapearDTO(productoEntity);
    	return productoDto;
    }
    


	public ProductoDto actualizar(int id,ProductoDto productoDto) {
    	//buscar por Id
    	ProductoDto antiguoProductoDto=buscarPorId(id);
    	ProductoEntity antiguoProductoEntidad=mapearEntidad(antiguoProductoDto);
    	
    	ProductoEntity productoEntidad=mapearEntidad(productoDto);
    	
    	antiguoProductoEntidad.setNombre(productoEntidad.getNombre());
    	antiguoProductoEntidad.setDescripcion(productoEntidad.getDescripcion());
    	antiguoProductoEntidad.setAfectacionImpuesto(productoEntidad.getAfectacionImpuesto());
    	antiguoProductoEntidad.setModeloAlterno(productoEntidad.getModeloAlterno());
    	antiguoProductoEntidad.setPrecioCompra(productoEntidad.getPrecioCompra());
    	antiguoProductoEntidad.setPrecioVenta(productoEntidad.getPrecioVenta());
    	
    	productoEntidad=productoRepository.save(antiguoProductoEntidad);
    	
    	productoDto=mapearDTO(productoEntidad);
    	return productoDto;
    }
    
    public void eliminar(ProductoDto productoDto) {
    	ProductoEntity productoEntity=mapearEntidad(productoDto);
    	productoRepository.deleteById(productoEntity.getIdProducto());
    }
    
}
