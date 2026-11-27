package com.wrinklewars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@org.springframework.scheduling.annotation.EnableScheduling
@SpringBootApplication
public class WrinkleWarsBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(WrinkleWarsBackendApplication.class, args);
	}

}
