package com.machines.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MachinesApplication {
	public static void main(final String[] args) {
		SpringApplication.run(MachinesApplication.class, args);
	}

}

