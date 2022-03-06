package com.java.main.event;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.java.main.mappers.LocationMapper;
import com.java.main.models.entity.LocationEntity;
import com.java.main.repositories.LocationRepository;

@Slf4j
@Component
public class LocationSaveEventListener {

	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private LocationMapper locationMapper;

	@EventListener
	public void onApplicationEvent(final LocationSaveEvent event) {
		log.debug("Event LocationSaveEvent ......");
		LocationEntity locationEntity = locationMapper.toEntity(event.getLocation());
		locationRepository.save(locationEntity);
	}
}
