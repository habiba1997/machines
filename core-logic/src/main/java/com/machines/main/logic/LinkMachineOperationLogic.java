package com.machines.main.logic;

import java.util.List;

import org.springframework.stereotype.Component;

import com.machines.main.dtos.Machine;
import com.machines.main.dtos.Operation;
import com.machines.main.models.enums.Status;
import com.machines.main.trigger.events.MachineOperationLinkEvent;

@Component
public class LinkMachineOperationLogic {

	public Validation<Operation> linkMachineOperation(final Operation operation, final Machine machine) {
		Validation<Operation> validation = new Validation<>();
		if (List.of(Status.CLOSED, Status.NONE, Status.IN_PRODUCTION).contains(operation.getStatus())) {
			validation.addFailMessage("Operation %s is in %s status, need to be in planned Status to be linked to Machine %s",
					operation.getName(), operation.getStatus().name(), machine.getName());
			return validation;
		}
		validation.addEvent(MachineOperationLinkEvent.builder().operationName(operation.getName()).machineName(machine.getName()).build());
		validation.addSuccessMessage("Operation %s is now linked to %s", operation.getName(), machine.getName());
		return validation;
	}
}
