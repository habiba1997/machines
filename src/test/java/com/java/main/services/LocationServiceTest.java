package com.java.main.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.java.main.cache.helpers.GoogleCacheService;
import com.java.main.cache.service.BaseCacheService;
import com.java.main.dtos.Location;
import com.java.main.impl.LocationServiceImpl;
import com.java.main.mappers.LocationMapper;
import com.java.main.repositories.LocationRepository;
import com.java.main.services.testdata.LocationTestData;

@RunWith(MockitoJUnitRunner.class)
public class LocationServiceTest {

	public static final String LOCATION_NAME = LocationTestData.LOCATION_NAME;
	public static final Long LOCATION_KEY = LocationTestData.LOCATION_KEY;

	@Mock
	private LocationRepository locationRepository;

	@Spy
	private LocationMapper mapper = Mappers.getMapper(LocationMapper.class);

	@Spy
	private BaseCacheService<Long, Location> mesCache = new GoogleCacheService<>();

	@InjectMocks
	private LocationService locationService = new LocationServiceImpl();

	@Before
	public void setup() {
		when(locationRepository.findAll()).thenReturn(List.of(LocationTestData.generateLocationEntity()));
	}

	@Test
	@DisplayName("Testing Finding all locations and correctly mapping locationEntity to location")
	public void testFindAll() {
		List<Location> locations = locationService.findAll();

		assertEquals(1, locations.size());
		assertEquals(LOCATION_NAME, locations.get(0).getName());
		assertNotNull(locations.get(0));
	}

	@Test
	@DisplayName("Testing finding location by name")
	public void testFindByName() {
		Location location = locationService.findByName(LOCATION_NAME);

		assertNotNull(location);
		assertEquals(LOCATION_NAME, location.getName());

	}

	@Test
	@DisplayName("Testing finding location by key")
	public void testFindByKey() {
		Location location = locationService.findByKey(LOCATION_KEY);

		assertNotNull(location);
		assertEquals(LOCATION_NAME, location.getName());

	}

}