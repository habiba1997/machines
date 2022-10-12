package com.machines.main.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.machines.main.aop.KafkaAnnotation;
import com.machines.main.dtos.MachineOperation;
import com.machines.main.dtos.Operation;
import com.machines.main.kafka.TopicName;
import com.machines.main.logic.UnLinkMachineOperationLogic;
import com.machines.main.logic.Validation;
import com.machines.main.response.Response;
import com.machines.main.services.MachineOperationService;
import com.machines.main.services.OperationService;
import com.machines.main.trigger.EventPublisher;

@Service
public class UnLinkMachineOperationService {

	@Autowired
	private UnLinkMachineOperationLogic logic;

	@Autowired
	private OperationService operationService;

	@Autowired
	private MachineOperationService machineService;


	@Autowired
	private EventPublisher eventPublisher;

	@KafkaAnnotation(topicName = TopicName.UNLINK_OPERATION_TO_MACHINE_TOPIC)
	public Response<Void> unlinkMachineOperation(final String operationName, final String machineName) {
		MachineOperation machineOperation = machineService.findByNameOrThrow(machineName);

		Validation<Operation> validation = logic.unlinkMachineOperation(machineOperation.getOperation(), machineOperation.getMachine());
		if (validation.isSuccess()) {
			eventPublisher.publishEvents(validation.getEvents());
		}
		return Response.<Void>builder().success(validation.isSuccess()).messages(validation.getMessages()).build();
	}
}
