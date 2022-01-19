package com.java.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.main.services.CacheService;

@RestController
public class CacheController {

	@Autowired
	private CacheService cacheService;

	@GetMapping(value = "/clear-cache")
	public ResponseEntity<Void> clearCache() {
		this.cacheService.clearAllSpringCache();
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(value = "/clear-machine-cache")
	public ResponseEntity<Void> clearMachineCache() {
		this.cacheService.clearMachinesCache();
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
