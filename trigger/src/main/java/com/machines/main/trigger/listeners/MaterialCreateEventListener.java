package com.machines.main.trigger.listeners;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.machines.main.mappers.MaterialMapper;
import com.machines.main.models.entity.MaterialEntity;
import com.machines.main.repositories.MaterialRepository;
import com.machines.main.trigger.events.MaterialCreateEvent;

@Slf4j
@Component
public class MaterialCreateEventListener {

	@Autowired
	private MaterialRepository materialRepository;

	@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
	@Autowired
	private MaterialMapper materialMapper;

	@EventListener
	public void onApplicationEvent(final MaterialCreateEvent event) {
		log.debug("Event MaterialCreateEvent ......");
		MaterialEntity materialEntity = materialMapper.toEntity(event.getMaterial());
		materialRepository.save(materialEntity);
	}
}
