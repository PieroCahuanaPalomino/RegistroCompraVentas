package com.proyect.web.product.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyect.web.product.dto.CategoriaDto;
import com.proyect.web.product.entity.CategoriaEntity;
import com.proyect.web.product.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
    @Autowired
    private ModelMapper modelMapper;
    
    private CategoriaDto mapearDTO(CategoriaEntity categoriaEntity){
    	CategoriaDto categoriaDto=modelMapper.map(categoriaEntity, CategoriaDto.class);
    	return categoriaDto;
    }
    
    private CategoriaEntity mapearEntidad(CategoriaDto categoriaDto) {
    	CategoriaEntity categoriaEntity=modelMapper.map(categoriaDto, CategoriaEntity.class);
    	return categoriaEntity;
    }
    
    public CategoriaDto guardarCategoria(CategoriaDto categoriaDto) {
    	CategoriaEntity categoriaEntity=mapearEntidad(categoriaDto);
    	CategoriaEntity nuevaCategoriaEntity=categoriaRepository.save(categoriaEntity);
    	CategoriaDto nuevaCategoriaDto=mapearDTO(nuevaCategoriaEntity);
    	return nuevaCategoriaDto;
    }
    
    public List<CategoriaDto> listaCategoria() {
    	List<CategoriaEntity> categoriasEntity=categoriaRepository.findAll();
    	List<CategoriaDto> categoriasDto=categoriasEntity
    										.stream()
    										.map(categoriaEntity->modelMapper.map(categoriaEntity,CategoriaDto.class))
    										.collect(Collectors.toList());
    	return categoriasDto;
    }
    
    public CategoriaDto buscarPorId(int id) {
    	CategoriaEntity categoriaEntity=categoriaRepository.findById(id).orElse(null);
    	if(categoriaEntity==null) {
    		return null;
    	}
    	CategoriaDto categoriaDto=mapearDTO(categoriaEntity);
    	return categoriaDto;
    }
    
    
    public boolean existeNombre(String nombre) {
    	return categoriaRepository.existsByNombre(nombre);
    }
    
    public boolean existeId(int id) {
    	return categoriaRepository.existsById(id);
    }
    
    public CategoriaDto actualizar(int id,CategoriaDto categoriaDto) {
    	//buscar por Id
    	CategoriaDto antiguoCategoriaDto=buscarPorId(id);
    	CategoriaEntity antiguoCategoriaEntidad=mapearEntidad(antiguoCategoriaDto);
    	
    	CategoriaEntity categoriaEntidad=mapearEntidad(categoriaDto);
    	
    	antiguoCategoriaEntidad.setNombre(categoriaEntidad.getNombre());
    	
    	categoriaEntidad=categoriaRepository.save(antiguoCategoriaEntidad);
    	
    	categoriaDto=mapearDTO(categoriaEntidad);
    	return categoriaDto;
    }
    
    public void eliminar(CategoriaDto categoriaDto) {
    	CategoriaEntity categoriaEntity=mapearEntidad(categoriaDto);
    	categoriaRepository.deleteById(categoriaEntity.getIdCategoria());
    }
}
