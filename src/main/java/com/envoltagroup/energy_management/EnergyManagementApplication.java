package com.envoltagroup.energy_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class EnergyManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnergyManagementApplication.class, args);
	}

}
