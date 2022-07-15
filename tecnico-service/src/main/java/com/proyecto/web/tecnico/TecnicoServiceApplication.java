package com.proyecto.web.tecnico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TecnicoServiceApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(TecnicoServiceApplication.class, args);
	}

}
