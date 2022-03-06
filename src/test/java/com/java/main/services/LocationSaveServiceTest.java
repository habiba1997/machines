package com.java.main.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.java.main.controller.request.LocationRequest;
import com.java.main.event.EventPublisher;
import com.java.main.event.LocationSaveEvent;

@RunWith(MockitoJUnitRunner.class)
public class LocationSaveServiceTest {

	@Mock
	private EventPublisher eventPublisher;

	@InjectMocks
	private LocationSaveService saveService;

	@Test
	public void saveLocation() {
		saveService.saveLocation(LocationRequest.builder().name("loc1").temp(true).type("on_door").build());
		verify(eventPublisher).publish(any(LocationSaveEvent.class));
	}
}