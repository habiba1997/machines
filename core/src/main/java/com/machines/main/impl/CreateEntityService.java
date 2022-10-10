package com.machines.main.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.machines.main.dtos.Location;
import com.machines.main.dtos.Machine;
import com.machines.main.dtos.Material;
import com.machines.main.dtos.Operation;
import com.machines.main.dtos.ProductionOrder;
import com.machines.main.logic.CreateEntityLogic;
import com.machines.main.logic.Validation;
import com.machines.main.request.LocationRequest;
import com.machines.main.request.MachineRequest;
import com.machines.main.request.MaterialRequest;
import com.machines.main.request.MeasuredValueUtils;
import com.machines.main.request.OperationRequest;
import com.machines.main.request.ProductionOrderRequest;
import com.machines.main.response.Response;
import com.machines.main.services.LocationService;
import com.machines.main.services.MaterialService;
import com.machines.main.services.ProductionOrderService;
import com.machines.main.trigger.EventPublisher;

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
		Validation<Material> validation = createEntityLogic.buildMaterial(request.getName(),
				MeasuredValueUtils.convertToMeasuredValue(request.getMeasuredValue()), request.getBaseUnit());
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
