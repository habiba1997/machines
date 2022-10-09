package com.java.main.trigger.listeners;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.java.main.converters.ConverterEnumsClass;
import com.java.main.error.NotFoundException;
import com.java.main.models.entity.OperationEntity;
import com.java.main.repositories.OperationRepository;
import com.java.main.trigger.events.OperationStatusChangeEvent;

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
