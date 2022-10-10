package com.machines.main.trigger.listeners;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.machines.main.converters.ConverterEnumsClass;
import com.machines.main.error.NotFoundException;
import com.machines.main.models.entity.OperationEntity;
import com.machines.main.repositories.OperationRepository;
import com.machines.main.trigger.events.OperationStatusChangeEvent;

@Slf4j
@Component
public class OperationStatusChangeEventListener {

	@Autowired
	private OperationRepository operationRepository;

	@EventListener
	public void onApplicationEvent(final OperationStatusChangeEvent event) {
		log.debug("Event OperationStatusChangeEvent ......");
		OperationEntity operationEntity = operationRepository.findByName(event.getOperationName())
				.orElseThrow(() -> new NotFoundException(String.format("Operation %s doesn't exist", event.getOperationName())));
		operationEntity.setStatus(ConverterEnumsClass.OPERATION_STATUS.toEntity(event.getNewStatus()));
		operationRepository.save(operationEntity);
	}
}
