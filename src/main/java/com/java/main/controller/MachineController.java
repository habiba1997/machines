package com.java.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.main.dtos.Machine;
import com.java.main.interfaces.services.MachineService;

@RestController
public class MachineController {

	@Autowired
	private MachineService machineService;

	@GetMapping(value = "/machines")
	public ResponseEntity<List<Machine>> getMachinesList() {
		return new ResponseEntity<>(this.machineService.findAll(), HttpStatus.OK);
	}

	@GetMapping(value = "/machines/location")
	public ResponseEntity<List<Machine>> getMachinesByLocationName(@RequestParam(name = "name") final String locationName) {
		return new ResponseEntity<>(this.machineService.findByLocationName(locationName), HttpStatus.OK);
	}

}
