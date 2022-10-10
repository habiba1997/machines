package com.machines.main.trigger.listeners;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.machines.main.error.NotFoundException;
import com.machines.main.models.entity.MachineEntity;
import com.machines.main.models.entity.OperationEntity;
import com.machines.main.repositories.MachineRepository;
import com.machines.main.repositories.OperationRepository;
import com.machines.main.trigger.events.MachineOperationLinkEvent;

@Slf4j
@Component
public class MachineOperationLinkEventListener {

	@Autowired
	private OperationRepository operationRepository;

	@Autowired
	private MachineRepository machineRepository;

	@EventListener
	public void onApplicationEvent(final MachineOperationLinkEvent event) {
		log.debug("Event MachineOperationLinkEvent ......");
		OperationEntity operationEntity = operationRepository.findByName(event.getOperationName())
				.orElseThrow(() -> new NotFoundException(String.format("Operation %s doesn't exist", event.getOperationName())));

		MachineEntity machineEntity = machineRepository.findByName(event.getMachineName())
				.orElseThrow(() -> new NotFoundException(String.format("Machine %s doesn't exist", event.getMachineName())));
		operationEntity.setMachineKey(machineEntity.getKey());
		operationRepository.save(operationEntity);
	}
}
