package com.machines.main.services;

import java.util.List;
import java.util.Optional;

import com.machines.main.dtos.Location;

public interface LocationService {

	List<Location> findAll();

	Location findByName(String name);

	Long findLocationKey(String locationName);

	Optional<Location> findByKey(long key);

}
