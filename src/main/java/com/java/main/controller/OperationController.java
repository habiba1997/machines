package com.java.main.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.main.models.dtos.OperationDTO;
import com.java.main.models.dtos.operation.OperationDTOWithMaterialMachine;
import com.java.main.services.OperationService;

@RestController
public class OperationController {

	@Autowired
	private OperationService operationService;

	@GetMapping(value = "/operations-status")
	public ResponseEntity<Set<OperationDTO>> getAllOperationsWithSetupAndInOverEndingProductionStatus() {
		return new ResponseEntity<>(this.operationService.getAllOperationsWithSetupAndInOverEndingProductionStatus(), HttpStatus.OK);
	}

	@GetMapping(value = "/operation/{id}")
	public ResponseEntity<OperationDTOWithMaterialMachine> getOperationById(@PathVariable("id") int id) {
		return new ResponseEntity<>(this.operationService.getOperationById(id), HttpStatus.OK);
	}

	@PutMapping(value = "/operation/{id}")
	public ResponseEntity<OperationDTOWithMaterialMachine> togglePercentageColor(@PathVariable("id") int id) {
		return new ResponseEntity<>(this.operationService.togglePercentageColor(id), HttpStatus.OK);
	}

	@GetMapping(value = "/operation-with-status/{id}")
	public ResponseEntity<OperationDTOWithMaterialMachine> operationIfSpecificProductionOrder(@PathVariable("id") int id) {
		return new ResponseEntity<>(this.operationService.getOperationIfWithSetupInOverEndingProductionOrder(id), HttpStatus.OK);
	}
}
