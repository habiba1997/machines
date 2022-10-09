package com.java.main.trigger.listeners;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.java.main.mappers.MachineMapper;
import com.java.main.models.entity.MachineEntity;
import com.java.main.repositories.MachineRepository;
import com.java.main.trigger.events.MachineCreateEvent;

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
