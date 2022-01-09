package com.java.main.services;

import java.util.List;

import com.java.main.dtos.Location;

public interface LocationService {

	List<Location> findAll();

	Location findByName(String name);
}
