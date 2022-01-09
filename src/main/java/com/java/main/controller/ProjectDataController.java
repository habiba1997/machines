package com.java.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectDataController {

	@Autowired
	private Environment environment;

	@GetMapping(value = "/gataway")
	public ResponseEntity<String> getGataway() {
		return new ResponseEntity<>(environment.getProperty("gataway") + " " + environment.getProperty("spring.cache.ehcache.config"), HttpStatus.OK);
	}

	@GetMapping(value = "/database-url")
	public ResponseEntity<String> getDatabaseUrl() {
		return new ResponseEntity<>(environment.getProperty("spring.datasource.url"), HttpStatus.OK);
	}

	@GetMapping(value = "/database-driver")
	public ResponseEntity<String> getDatabaseDriver() {
		return new ResponseEntity<>(environment.getProperty("spring.datasource.driver-class-name"), HttpStatus.OK);
	}

}
