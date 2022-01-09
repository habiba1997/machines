package com.java.main.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@Override
	public List<Location> findAll() {
		return mapper.toModels(repository.findAll());
	}

	@Override
	public Location findByName(final String locationName) {
		return mapper.toModel(repository.findByName(locationName));
	}

}
