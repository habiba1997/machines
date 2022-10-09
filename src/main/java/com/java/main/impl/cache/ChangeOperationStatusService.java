package com.java.main.impl.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.main.dtos.Operation;
import com.java.main.interfaces.services.OperationService;
import com.java.main.logic.ChangeOperationStatusLogic;
import com.java.main.logic.Validation;
import com.java.main.models.enums.Status;
import com.java.main.response.Response;
import com.java.main.trigger.EventPublisher;

@Service
public class ChangeOperationStatusService {

	@Autowired
	private ChangeOperationStatusLogic logic;

	@Autowired
	private OperationService operationService;

	@Autowired
	private EventPublisher eventPublisher;

	public Response<Operation> changeOperationStatus(final String operationName, final Status operationNewStatus) {
		Operation operation = operationService.findByName(operationName);
		Validation<Operation> validation = logic.changeOperationStatus(operation, operationNewStatus);
		if (validation.isSuccess()) {
			eventPublisher.publishEvents(validation.getEvents());
			return Response.<Operation>builder().messages(validation.getMessages()).body(validation.getBody()).build();
		}
		return Response.<Operation>builder().messages(validation.getMessages()).build();
	}
}
