package com.proyect.web.compra;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import com.proyect.web.compra.entity.CarritoCompraEntity;
import com.proyect.web.compra.repository.CarritoCompraRepository;

@SpringBootApplication
@EnableEurekaClient
public class CompraServiceApplication implements CommandLineRunner{
	private static Logger LOG=LoggerFactory.getLogger(CompraServiceApplication.class);
	

	
	
	

	public static void main(String[] args) {
		SpringApplication.run(CompraServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	/**/
	
	}

}
