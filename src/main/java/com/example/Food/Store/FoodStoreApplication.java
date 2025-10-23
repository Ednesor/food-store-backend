package com.example.Food.Store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FoodStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodStoreApplication.class, args);
	}

}
