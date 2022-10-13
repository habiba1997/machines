package com.machines.main.controller.post;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.machines.main.dtos.Location;
import com.machines.main.dtos.Machine;
import com.machines.main.dtos.Material;
import com.machines.main.dtos.Operation;
import com.machines.main.dtos.ProductionOrder;
import com.machines.main.impl.CreateEntityService;
import com.machines.main.request.LocationRequest;
import com.machines.main.request.MachineRequest;
import com.machines.main.request.MaterialRequest;
import com.machines.main.request.OperationRequest;
import com.machines.main.request.ProductionOrderRequest;
import com.machines.main.response.Response;

@RestController
@RequestMapping("/create")
public class CreateController {

	@Autowired
	private CreateEntityService createEntityService;

	@PostMapping(value = "/location")
	public ResponseEntity<Response<Location>> postLocation(@Valid @RequestBody final LocationRequest locationRequest) {
		Response<Location> response = this.createEntityService.saveLocation(locationRequest);
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@PostMapping(value = "/material")
	public ResponseEntity<Response<Material>> postMaterial(@Valid @RequestBody final MaterialRequest materialRequest) {
		Response<Material> response = this.createEntityService.saveMaterial(materialRequest);
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@PostMapping(value = "/machine")
	public ResponseEntity<Response<Machine>> postMachine(@Valid @RequestBody final MachineRequest machineRequest) {
		Response<Machine> response = this.createEntityService.saveMachine(machineRequest);
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@PostMapping(value = "/operation")
	public ResponseEntity<Response<Operation>> postOperation(@Valid @RequestBody final OperationRequest operationRequest) {
		Response<Operation> response = this.createEntityService.saveOperation(operationRequest);
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@PostMapping(value = "/production-order")
	public ResponseEntity<Response<ProductionOrder>> postProductionOrder(@Valid @RequestBody final ProductionOrderRequest productionOrderRequest) {
		Response<ProductionOrder> response = this.createEntityService.saveProductionOrder(productionOrderRequest);
		return new ResponseEntity(response, HttpStatus.OK);
	}
}
