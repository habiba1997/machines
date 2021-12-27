package com.java.main.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.main.event.EventPublisher;
import com.java.main.models.dtos.MachineDTO;
import com.java.main.services.MachineService;

@RestController
public class MachineController {

	@Autowired
	private MachineService machineService;

	@Autowired
	private EventPublisher eventPublisher;

	@GetMapping(value = "/machines")
	public ResponseEntity<Set<MachineDTO>> getAllMachines() {
		ResponseEntity<Set<MachineDTO>> responce = new ResponseEntity<>(this.machineService.getAllMachines(), HttpStatus.OK);
		if (responce.getStatusCode() == HttpStatus.OK) {
			eventPublisher.publishCustomEvent("Get My Machines", true);
		}
		return responce;
	}

	@GetMapping(value = "/machines-status")
	public ResponseEntity<Set<MachineDTO>> getAllMachinesSpecificProductionOrder() {
		return new ResponseEntity<>(this.machineService.getAllMachinesWithSetupAndInOverEndingProductionStatus(), HttpStatus.OK);
	}

}
