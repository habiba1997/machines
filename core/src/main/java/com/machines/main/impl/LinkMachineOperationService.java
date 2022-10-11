package com.machines.main.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.machines.main.dtos.MachineOperation;
import com.machines.main.dtos.Operation;
import com.machines.main.logic.LinkMachineOperationLogic;
import com.machines.main.logic.Validation;
import com.machines.main.response.Response;
import com.machines.main.services.MachineOperationService;
import com.machines.main.services.OperationService;
import com.machines.main.trigger.EventPublisher;

@Service
public class LinkMachineOperationService {

	@Autowired
	private LinkMachineOperationLogic logic;

	@Autowired
	private OperationService operationService;

	@Autowired
	private MachineOperationService machineService;

	@Autowired
	private EventPublisher eventPublisher;

	public Response<Void> linkMachineOperation(final String operationName, final String machineName) {
		MachineOperation machine = machineService.findByName(machineName);
		Operation operation = operationService.findByName(operationName);

		Validation<Operation> validation = logic.linkMachineOperation(operation, machine);
		if (validation.isSuccess()) {
			eventPublisher.publishEvents(validation.getEvents());
		}
		return Response.<Void>builder().messages(validation.getMessages()).build();
	}
}
