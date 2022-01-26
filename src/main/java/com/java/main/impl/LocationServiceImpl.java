package com.java.main.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.main.cache.service.CacheService;
import com.java.main.dtos.Location;
import com.java.main.mappers.LocationMapper;
import com.java.main.profile.CacheConstants;
import com.java.main.repositories.LocationRepository;
import com.java.main.services.LocationService;

@Service
public class LocationServiceImpl extends CacheService<Long, Location> implements LocationService {

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
		return this.fetchAndLoadAllCachedEntries().values().stream().filter(l -> l.getName().equals(locationName)).findFirst().orElse(null);
	}

	@Override
	public Location findByKey(final long locationKey) {
		return this.fetchAndLoadAllCachedEntries().get(locationKey);
	}

	@Override
	protected String getCacheName() {
		return CacheConstants.LOCATION;
	}

	@Override
	protected List<Location> findAllItemsFromDatabase() {
		return mapper.toModels(repository.findAll());
	}
}
