package com.java.main.controller.post;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.main.controller.request.LocationRequest;
import com.java.main.controller.request.MachineRequest;
import com.java.main.controller.request.MaterialRequest;
import com.java.main.controller.request.OperationRequest;
import com.java.main.controller.request.ProductionOrderRequest;
import com.java.main.dtos.Location;
import com.java.main.dtos.Machine;
import com.java.main.dtos.Material;
import com.java.main.dtos.Operation;
import com.java.main.dtos.ProductionOrder;
import com.java.main.impl.CreateEntityService;
import com.java.main.response.Response;

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
