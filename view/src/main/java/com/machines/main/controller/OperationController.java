package com.machines.main.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.machines.main.dtos.Operation;
import com.machines.main.models.enums.Status;
import com.machines.main.services.OperationService;

@RestController
public class OperationController {

	@Autowired
	private OperationService operationService;

	@GetMapping(value = "/operations")
	public ResponseEntity<List<Operation>> getOperationsInStatus(@RequestParam("statuses") final String statusList) {
		List<Status> statuses = Status.getEnum(List.of(statusList.split(",")).stream().map(String::toUpperCase).collect(Collectors.toList()));
		return new ResponseEntity<>(this.operationService.findByStatusIn(statuses), HttpStatus.OK);
	}

	@GetMapping(value = "/operations/production-order")
	public ResponseEntity<List<Operation>> getOperationsByProductionOrderName(@RequestParam("name") final String productionOrderName) {
		List<Operation> operations = this.operationService.findByProductionOrderName(productionOrderName);
		return new ResponseEntity<>(operations, HttpStatus.OK);
	}

}
