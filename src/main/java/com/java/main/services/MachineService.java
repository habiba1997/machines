package com.java.main.services;

import java.util.List;

import com.java.main.dtos.Machine;

public interface MachineService {

	List<Machine> findAll();

	List<Machine> findByLocationName(String locationName);

}
