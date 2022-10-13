package com.machines.main.logic;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.machines.main.dtos.Location;
import com.machines.main.dtos.Machine;
import com.machines.main.dtos.Material;
import com.machines.main.dtos.Operation;
import com.machines.main.dtos.Order;
import com.machines.main.dtos.ProductionOrder;
import com.machines.main.models.enums.LocationType;
import com.machines.main.models.enums.MachineType;
import com.machines.main.models.enums.Status;
import com.machines.main.models.helpers.MeasuredValue;
import com.machines.main.models.helpers.MesUnit;
import com.machines.main.trigger.events.LocationCreateEvent;
import com.machines.main.trigger.events.MachineCreateEvent;
import com.machines.main.trigger.events.MaterialCreateEvent;
import com.machines.main.trigger.events.OperationCreateEvent;
import com.machines.main.trigger.events.ProductionOrderCreateEvent;

@Component
public class CreateEntityLogic {

	public Validation<Location> buildLocation(final String name, final boolean temp, final String type) {
		Validation<Location> validation = new Validation<>();
		Location location = Location.builder()
				.name(name)
				.temp(temp)
				.type(!type.isBlank() ? LocationType.getLocationType(type) : LocationType.IN_DOOR)
				.build();
		validation.setBody(location);
		validation.addEvent(LocationCreateEvent.builder().location(location).build());
		validation.addSuccessMessage(SuccessMessages.LOCATION_CREATED_SUCCESSFULLY, name);
		return validation;
	}

	public Validation<Material> buildMaterial(final String name, final MeasuredValue measuredValue, final MesUnit baseUnit) {
		Validation<Material> validation = new Validation<>();
		Material material = Material.builder()
				.name(name)
				.measuredValue(measuredValue)
				.baseUnit(baseUnit != null ? baseUnit : MesUnit.valueOf(measuredValue.getUnit().getBaseUnit()))
				.build();
		validation.setBody(material);
		validation.addEvent(MaterialCreateEvent.builder().material(material).build());
		validation.addSuccessMessage(SuccessMessages.MATERIAL_CREATED_SUCCESSFULLY, name);
		return validation;
	}

	public Validation<Machine> buildMachine(final String name, final Location location, final MachineType type) {
		Validation<Machine> validation = new Validation<>();
		Machine machine = Machine.builder()
				.name(name)
				.location(location)
				.machineType(type != null ? type : MachineType.NONE)
				.build();
		validation.setBody(machine);
		validation.addEvent(MachineCreateEvent.builder().machine(machine).build());
		validation.addSuccessMessage(SuccessMessages.MACHINE_CREATED_SUCCESSFULLY, name);
		return validation;

	}

	public Validation<ProductionOrder> buildProductionOrder(final String name, final Material material, final MeasuredValue plannedQuantity) {
		Validation<ProductionOrder> validation = new Validation<>();
		ProductionOrder productionOrder = ProductionOrder.builder()
				.name(name)
				.startDate(ZonedDateTime.now())
				.material(material)
				.operationList(List.of())
				.plannedQuantity(plannedQuantity)
				.build();
		validation.setBody(productionOrder);
		validation.addEvent(ProductionOrderCreateEvent.builder().productionOrder(productionOrder).build());
		validation.addSuccessMessage(SuccessMessages.PO_CREATED_SUCCESSFULLY, name);
		return validation;

	}

	public Validation<Operation> buildOperation(final String name, final Material material, final ProductionOrder po, final Status status) {
		Order order = Order.builder()
				.plannedQuantity(po.getPlannedQuantity())
				.name(po.getName())
				.material(po.getMaterial())
				.startDate(po.getStartDate()).build();
		Validation<Operation> validation = new Validation<>();
		Operation operation = Operation.builder()
				.name(name)
				.status(status != null ? status : Status.PLANNED)
				.productionOrder(order)
				.material(material)
				.build();
		validation.setBody(operation);
		validation.addEvent(OperationCreateEvent.builder().operation(operation).build());
		validation.addSuccessMessage(SuccessMessages.OPERATION_CREATED_SUCCESSFULLY, name);
		return validation;
	}

}
