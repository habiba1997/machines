package com.machines.main.trigger.listeners;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.machines.main.mappers.MachineMapper;
import com.machines.main.models.entity.MachineEntity;
import com.machines.main.repositories.MachineRepository;
import com.machines.main.trigger.events.MachineCreateEvent;

@Slf4j
@Component
public class MachineCreateEventListener {

	@Autowired
	private MachineRepository machineRepository;

	@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
	@Autowired
	private MachineMapper machineMapper;

	@EventListener
	public void onApplicationEvent(final MachineCreateEvent event) {
		log.debug("Event MachineCreateEvent ......");
		MachineEntity machineEntity = machineMapper.toEntity(event.getMachine());
		machineRepository.save(machineEntity);
	}
}
