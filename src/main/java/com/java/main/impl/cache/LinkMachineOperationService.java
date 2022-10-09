package com.java.main.impl.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.main.dtos.Machine;
import com.java.main.dtos.Operation;
import com.java.main.interfaces.services.MachineService;
import com.java.main.interfaces.services.OperationService;
import com.java.main.logic.LinkMachineOperationLogic;
import com.java.main.logic.Validation;
import com.java.main.response.Response;
import com.java.main.trigger.EventPublisher;

@Service
public class LinkMachineOperationService {

	@Autowired
	private LinkMachineOperationLogic logic;

	@Autowired
	private OperationService operationService;

	@Autowired
	private MachineService machineService;

	@Autowired
	private EventPublisher eventPublisher;

	public Response<Void> linkMachineOperation(final String operationName, final String machineName) {
		Operation operation = operationService.findByName(operationName);
		Machine machine = machineService.findByName(machineName);

		Validation<Operation> validation = logic.linkMachineOperation(operation, machine);
		if (validation.isSuccess()) {
			eventPublisher.publishEvents(validation.getEvents());
		}
		return Response.<Void>builder().messages(validation.getMessages()).build();
	}
}
