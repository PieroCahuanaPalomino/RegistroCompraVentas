package com.proyecto.web.tecnico.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigModellMapper {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
}
