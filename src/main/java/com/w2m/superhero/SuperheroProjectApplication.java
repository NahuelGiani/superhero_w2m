package com.w2m.superhero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableAutoConfiguration
@EnableCaching
@SpringBootApplication
public class SuperheroProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuperheroProjectApplication.class, args);
	}

}
