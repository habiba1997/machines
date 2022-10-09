package com.java.main.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.main.controller.request.LocationRequest;
import com.java.main.controller.request.MachineRequest;
import com.java.main.controller.request.MaterialRequest;
import com.java.main.controller.request.MeasuredValueUtils;
import com.java.main.controller.request.OperationRequest;
import com.java.main.controller.request.ProductionOrderRequest;
import com.java.main.dtos.Location;
import com.java.main.dtos.Machine;
import com.java.main.dtos.Material;
import com.java.main.dtos.Operation;
import com.java.main.dtos.ProductionOrder;
import com.java.main.interfaces.services.LocationService;
import com.java.main.interfaces.services.MaterialService;
import com.java.main.interfaces.services.ProductionOrderService;
import com.java.main.logic.CreateEntityLogic;
import com.java.main.logic.Validation;
import com.java.main.response.Response;
import com.java.main.trigger.EventPublisher;

@Service
public class CreateEntityService {

	@Autowired
	private EventPublisher eventPublisher;

	@Autowired
	private LocationService locationService;

	@Autowired
	private CreateEntityLogic createEntityLogic;

	@Autowired
	private MaterialService materialService;

	@Autowired
	private ProductionOrderService productionOrderService;

	@Transactional
	public Response<Location> saveLocation(final LocationRequest request) {
		Validation<Location> validation = createEntityLogic.buildLocation(request.getName(), request.isTemp(), request.getType());
		eventPublisher.publishEvents(validation.getEvents());
		return Response.<Location>builder().messages(validation.getMessages()).body(validation.getBody()).build();
	}

	@Transactional
	public Response<Material> saveMaterial(final MaterialRequest request) {
		Validation<Material> validation = createEntityLogic.buildMaterial(request.getName(), MeasuredValueUtils.convertToMeasuredValue(request.getMeasuredValue()), request.getBaseUnit());
		eventPublisher.publishEvents(validation.getEvents());
		return Response.<Material>builder().messages(validation.getMessages()).body(validation.getBody()).build();
	}

	@Transactional
	public Response<Machine> saveMachine(final MachineRequest request) {
		Location location = request.getLocationName() != null ? locationService.findByName(request.getLocationName()) : null;
		Validation<Machine> validation = createEntityLogic.buildMachine(request.getName(), location, request.getMachineType());
		eventPublisher.publishEvents(validation.getEvents());
		return Response.<Machine>builder().messages(validation.getMessages()).body(validation.getBody()).build();
	}

	@Transactional
	public Response<ProductionOrder> saveProductionOrder(final ProductionOrderRequest request) {
		Material material = materialService.findByName(request.getMaterialName());
		Validation<ProductionOrder> validation = createEntityLogic.buildProductionOrder(request.getName(), material,
				MeasuredValueUtils.convertToMeasuredValue(request.getPlannedQuantity()));
		eventPublisher.publishEvents(validation.getEvents());
		return Response.<ProductionOrder>builder().messages(validation.getMessages()).body(validation.getBody()).build();
	}

	@Transactional
	public Response<Operation> saveOperation(final OperationRequest request) {
		Material material = materialService.findByName(request.getMaterialName());
		ProductionOrder productionOrder = productionOrderService.findByName(request.getProductionOrderName());
		Validation<Operation> validation = createEntityLogic.buildOperation(request.getName(), material, productionOrder, request.getStatus());
		eventPublisher.publishEvents(validation.getEvents());
		return Response.<Operation>builder().messages(validation.getMessages()).body(validation.getBody()).build();
	}
}
