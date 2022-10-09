package com.java.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.main.dtos.Location;
import com.java.main.impl.CreateEntityService;
import com.java.main.interfaces.services.LocationService;

@RestController
public class LocationController {

	@Autowired
	private LocationService locationService;

	@Autowired
	private CreateEntityService createEntityService;

	@GetMapping(value = "/locations")
	public ResponseEntity<List<Location>> getAllLocations() {
		return new ResponseEntity<>(this.locationService.findAll(), HttpStatus.OK);
	}

	@GetMapping(value = "/location")
	public ResponseEntity<Location> getLocationByName(@RequestParam(name = "name") final String locationName) {
		return new ResponseEntity<>(this.locationService.findByName(locationName), HttpStatus.OK);
	}


}
