package com.java.main.trigger;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.java.main.interfaces.testdata.LocationTestData;
import com.java.main.mappers.LocationMapper;
import com.java.main.repositories.LocationRepository;
import com.java.main.trigger.events.LocationCreateEvent;
import com.java.main.trigger.listeners.LocationCreateEventListener;

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