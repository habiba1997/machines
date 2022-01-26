package com.java.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.main.cache.service.ClearCacheService;

@RestController
public class CacheController {

	@Autowired
	private ClearCacheService clearCacheService;

	@GetMapping(value = "/clear-cache")
	public ResponseEntity<Void> clearCache() {
		this.clearCacheService.clearAllSpringCache();
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
