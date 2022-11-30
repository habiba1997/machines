package com.machines.main.logic;

import java.util.List;

import org.springframework.stereotype.Component;

import com.machines.main.dtos.Machine;
import com.machines.main.dtos.Operation;
import com.machines.main.models.enums.Status;
import com.machines.main.trigger.events.MachineOperationUnLinkEvent;

@Component
public class UnLinkMachineOperationLogic {

	public Validation<Operation> unlinkMachineOperation(final Operation operation, final Machine machine, final String operationName) {
		Validation<Operation> validation = new Validation<>();
		if (!operation.getName().equals(operationName)) {
			validation.addFailMessage("Operation %s is not linked to machine %s, but rather operation %s is the one linked to the machine",
					operationName, machine.getName(), operation.getName());
			return validation;
		}
		if (!List.of(Status.CLOSED, Status.PLANNED, Status.SETUP).contains(operation.getStatus())) {
			validation.addFailMessage("Can't unlink operation %s from machine %s because it is in %s status",
					operation.getName(), machine.getName(), operation.getStatus().name());
			return validation;
		}
		validation.addEvent(MachineOperationUnLinkEvent.builder().operationName(operation.getName()).machineName(machine.getName()).build());
		validation.addSuccessMessage("Operation %s is now unlinked from %s", operation.getName(), machine.getName());
		return validation;
	}
}
