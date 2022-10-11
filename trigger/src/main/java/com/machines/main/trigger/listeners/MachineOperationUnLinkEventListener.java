package com.machines.main.trigger.listeners;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.machines.main.error.NotFoundException;
import com.machines.main.models.entity.OperationEntity;
import com.machines.main.repositories.OperationRepository;
import com.machines.main.trigger.events.MachineOperationUnLinkEvent;

@Slf4j
@Component
public class MachineOperationUnLinkEventListener {

	@Autowired
	private OperationRepository operationRepository;

	@EventListener
	public void onApplicationEvent(final MachineOperationUnLinkEvent event) {
		log.debug("Event MachineOperationUnLinkEvent ......");
		OperationEntity operationEntity = operationRepository.findByName(event.getOperationName())
				.orElseThrow(() -> new NotFoundException(String.format("Operation %s doesn't exist", event.getOperationName())));
		operationEntity.setMachineEntity(null);
		operationRepository.save(operationEntity);
	}
}
