package com.machines.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.machines.main.dtos.MachineOperation;
import com.machines.main.services.MachineOperationService;

@RestController
public class MachineOperationController {

	@Autowired
	private MachineOperationService service;

	@GetMapping(value = "/machine-operations")
	public ResponseEntity<List<MachineOperation>> getMachineOperations() {
		List<MachineOperation> machineOperations = this.service.findAll();
		return new ResponseEntity<>(machineOperations, HttpStatus.OK);
	}
}
