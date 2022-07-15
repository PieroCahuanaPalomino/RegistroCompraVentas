package com.proyecto.web.venta;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.proyecto.web.venta.entity.ClienteEntity;
import com.proyecto.web.venta.repository.ClienteRepository;
import com.proyecto.web.venta.service.ClienteService;


@SpringBootApplication
@EnableFeignClients 
@EnableEurekaClient
public class VentaServiceApplication implements CommandLineRunner{
	private static Logger LOG=LoggerFactory.getLogger(VentaServiceApplication.class);

	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	ClienteService clienteService;
	
	public static void main(String[] args) {
		SpringApplication.run(VentaServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
/*
		
		boolean variable;
		variable=clienteRepository.existsByApellido("Cahuana");
		
		if(variable) {
			LOG.info("SI EXISTE UN NOMBRE CON ESE APELLIDO");
		}			
		String var=clienteService.traerLista("Pastel","Rambo");
		LOG.info(var);
		*/
	}


}
