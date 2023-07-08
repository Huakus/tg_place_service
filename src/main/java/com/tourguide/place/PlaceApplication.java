package com.tourguide.place;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
@EnableJpaAuditing
public class PlaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlaceApplication.class, args);
	}

	@Bean
	public AuditorAware<String> auditorProvider() {
		return () -> Optional.ofNullable("system"); // you can replace "system" with any string that identifies the current user or system
	}
}
