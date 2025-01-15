package com.api.rota_facil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RotaFacilApplication {

	public static void main(String[] args) {
		SpringApplication.run(RotaFacilApplication.class, args);
	}

}
