package com.machines.main.trigger;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.machines.main.mappers.LocationMapper;
import com.machines.main.repositories.LocationRepository;
import com.machines.main.testdata.LocationTestData;
import com.machines.main.trigger.events.LocationCreateEvent;
import com.machines.main.trigger.listeners.LocationCreateEventListener;

@RunWith(MockitoJUnitRunner.class)
public class LocationCreateEventListenerTest {
	@Mock
	private LocationRepository locationRepository;

	@Spy
	private LocationMapper locationMapper;

	@InjectMocks
	private LocationCreateEventListener listener;

	@Test
	public void onApplicationEvent() {
		LocationCreateEvent event = LocationCreateEvent.builder().location(LocationTestData.generateLocation(true)).build();
		listener.onApplicationEvent(event);
		verify(locationRepository).save(any());

	}
}