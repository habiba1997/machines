package com.java.main.impl;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.java.main.cache.service.CacheService;
import com.java.main.dtos.Location;
import com.java.main.mappers.LocationMapper;
import com.java.main.profile.CacheConstants;
import com.java.main.repositories.LocationRepository;
import com.java.main.services.LocationService;

@Service
@Slf4j
public class LocationServiceImpl extends CacheService<Long, Location> implements LocationService {

	// here is the overridden method getTTl
	@Value("${cache.ttl.location:}")
//	@Getter
	private Duration timeToLive;

	@Autowired
	private LocationRepository repository;

	@Autowired
	private LocationMapper mapper;

	@Override
	public List<Location> findAll() {
		return new ArrayList<>(this.fetchAndLoadAllCachedEntries().values());
	}

	@Override
	public Location findByName(final String locationName) {
		long startTime = System.currentTimeMillis();
		Map<Long, Location> locationMap = this.fetchAndLoadAllCachedEntries();
		long endTime = System.currentTimeMillis();
		log.info("Fetch & load all location cache entries in {} milliseconds", (endTime - startTime));
		return locationMap.values().stream().filter(l -> l.getName().equals(locationName)).findFirst().orElse(null);
	}

	@Override
	public Location findByKey(final long locationKey) {
		return this.getValueByKey(locationKey);
	}

	@Override
	protected String getCacheName() {
		return CacheConstants.LOCATION;
	}

	@Override
	protected Duration getTimeToLive() {
		return timeToLive;
	}

	@Override
	protected List<Location> findAllItemsFromDatabase() {
		return mapper.toModels(repository.findAll());
	}
}
