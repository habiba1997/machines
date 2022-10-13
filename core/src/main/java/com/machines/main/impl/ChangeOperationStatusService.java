package com.machines.main.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.machines.main.aop.KafkaAnnotation;
import com.machines.main.dtos.Operation;
import com.machines.main.kafka.TopicName;
import com.machines.main.logic.ChangeOperationStatusLogic;
import com.machines.main.logic.Validation;
import com.machines.main.models.enums.Status;
import com.machines.main.response.Response;
import com.machines.main.services.OperationService;
import com.machines.main.trigger.EventPublisher;

@Service
public class ChangeOperationStatusService {

	@Autowired
	private ChangeOperationStatusLogic logic;

	@Autowired
	private OperationService operationService;

	@Autowired
	private EventPublisher eventPublisher;

	@KafkaAnnotation(topicName = TopicName.OPERATION_STATUS_CHANGE_TOPIC)
	public Response<Operation> changeOperationStatus(final String operationName, final Status operationNewStatus) {
		Operation operation = operationService.findByName(operationName);
		Validation<Operation> validation = logic.changeOperationStatus(operation, operationNewStatus);
		if (validation.isSuccess()) {
			eventPublisher.publishEvents(validation.getEvents());
			return Response.<Operation>builder().success(validation.isSuccess()).messages(validation.getMessages()).body(validation.getBody()).build();
		}
		return Response.<Operation>builder().success(validation.isSuccess()).messages(validation.getMessages()).build();
	}
}
