package com.java.main.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.java.main.dtos.Location;
import com.java.main.mappers.LocationMapper;
import com.java.main.repositories.LocationRepository;
import com.java.main.services.LocationService;

@Service
public class LocationServiceImpl implements LocationService {
	@Autowired
	private LocationRepository repository;

	@Autowired
	private LocationMapper mapper;
//Implementations of cache interface are expected to be thread-safe, and can be safely accessed by multiple concurrent threads.
	private Cache<String, Map<Long, Location>> cache = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).build();

	@Override
	public List<Location> findAll() {
		return mapper.toModels(repository.findAll());
	}

	@Override
	public Location findByName(final String locationName) {
		return mapper.toModel(repository.findByName(locationName));
	}

}
