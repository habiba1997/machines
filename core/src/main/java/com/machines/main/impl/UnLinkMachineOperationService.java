package com.machines.main.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.machines.main.dtos.Machine;
import com.machines.main.dtos.Operation;
import com.machines.main.logic.UnLinkMachineOperationLogic;
import com.machines.main.logic.Validation;
import com.machines.main.response.Response;
import com.machines.main.services.MachineService;
import com.machines.main.services.OperationService;
import com.machines.main.trigger.EventPublisher;

@Service
public class UnLinkMachineOperationService {

	@Autowired
	private UnLinkMachineOperationLogic logic;

	@Autowired
	private OperationService operationService;

	@Autowired
	private MachineService machineService;

	@Autowired
	private EventPublisher eventPublisher;

	public Response<Void> unlinkMachineOperation(final String operationName, final String machineName) {
		Operation operation = operationService.findByName(operationName);
		Machine machine = machineService.findByName(machineName);

		Validation<Operation> validation = logic.unlinkMachineOperation(operation, machine);
		if (validation.isSuccess()) {
			eventPublisher.publishEvents(validation.getEvents());
		}
		return Response.<Void>builder().messages(validation.getMessages()).build();
	}
}
