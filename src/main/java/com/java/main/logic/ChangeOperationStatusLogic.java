package com.java.main.logic;

import org.springframework.stereotype.Component;

import com.java.main.dtos.Operation;
import com.java.main.models.enums.Status;
import com.java.main.trigger.events.OperationStatusChangeEvent;

@Component
public class ChangeOperationStatusLogic {

	public Validation<Operation> changeOperationStatus(final Operation operation, final Status newStatus) {
		Validation<Operation> validation = new Validation<>();
		Status oldStatus = operation.getStatus();
		if (operation.getStatus().equals(newStatus)) {
			validation.addFailMessage("Operation %s is already in %s status", operation.getName(), oldStatus.name());
			return validation;
		}
		validation.addEvent(OperationStatusChangeEvent.builder().operationName(operation.getName()).newStatus(newStatus).build());
		validation.addSuccessMessage("Operation %s status changes from %s to %s", operation.getName(), oldStatus.name(), newStatus.name());
		operation.setStatus(newStatus);
		return validation;
	}
}
