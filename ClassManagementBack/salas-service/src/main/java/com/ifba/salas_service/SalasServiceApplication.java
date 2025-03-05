package com.ifba.salas_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SalasServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalasServiceApplication.class, args);
	}

}
