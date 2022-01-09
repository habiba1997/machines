package com.java.main.services.testdata;

import com.java.main.dtos.Location;
import com.java.main.models.entity.LocationEntity;
import com.java.main.models.enums.LocationType;

public class LocationTestData {

	public static final String LOCATION_NAME = "location1";
	public static final int LOCATION_KEY = 1920;

	public static Location generateLocation() {
		return generateLocation(false);
	}

	public static Location generateLocation(final boolean temporary) {
		return Location.builder()
				.name(LOCATION_NAME)
				.key(LOCATION_KEY)
				.temp(temporary)
				.type(LocationType.FLOOR)
				.build();

	}

	public static LocationEntity generateLocationEntity() {
		return LocationEntity.builder()
				.key(LOCATION_KEY)
				.name(LOCATION_NAME)
				.temp(false)
				.type(LocationType.ON_DOOR)
				.build();

	}
}
