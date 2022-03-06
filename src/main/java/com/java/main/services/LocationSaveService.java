package com.java.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.main.controller.request.LocationRequest;
import com.java.main.dtos.Location;
import com.java.main.event.EventPublisher;
import com.java.main.event.LocationSaveEvent;
import com.java.main.models.enums.LocationType;

@Service
public class LocationSaveService {

	@Autowired
	private EventPublisher eventPublisher;

	@Transactional
	public void saveLocation(final LocationRequest locationRequest) {
		Location location = Location.builder()
				.name(locationRequest.getName())
				.temp(locationRequest.isTemp())
				.type(LocationType.getLocationType(locationRequest.getType()))
				.build();

		eventPublisher.publish(LocationSaveEvent.builder().location(location).build());
	}

}
