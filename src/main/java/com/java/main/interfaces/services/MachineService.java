package com.java.main.interfaces.services;

import java.util.List;

import com.java.main.dtos.Machine;

public interface MachineService {

	List<Machine> findAll();

	List<Machine> findByLocationName(String locationName);

	Machine findByName(String machineName);
}
