package com.java.main.event;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.java.main.mappers.LocationMapper;
import com.java.main.repositories.LocationRepository;
import com.java.main.services.testdata.LocationTestData;

@RunWith(MockitoJUnitRunner.class)
public class LocationSaveEventListenerTest {
	@Mock
	private LocationRepository locationRepository;

	@Spy
	private LocationMapper locationMapper;

	@InjectMocks
	private LocationSaveEventListener listener;

	@Test
	public void onApplicationEvent() {
		LocationSaveEvent event = LocationSaveEvent.builder().location(LocationTestData.generateLocation(true)).build();
		listener.onApplicationEvent(event);
		verify(locationRepository).save(any());

	}
}