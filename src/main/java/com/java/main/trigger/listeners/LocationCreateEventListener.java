package com.java.main.trigger.listeners;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.java.main.mappers.LocationMapper;
import com.java.main.models.entity.LocationEntity;
import com.java.main.repositories.LocationRepository;
import com.java.main.trigger.events.LocationCreateEvent;

@Slf4j
@Component
public class LocationCreateEventListener {

	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private LocationMapper locationMapper;

	@EventListener
	public void onApplicationEvent(final LocationCreateEvent event) {
		log.debug("Event LocationCreateEvent ......");
		LocationEntity locationEntity = locationMapper.toEntity(event.getLocation());
		locationRepository.save(locationEntity);
	}
}
