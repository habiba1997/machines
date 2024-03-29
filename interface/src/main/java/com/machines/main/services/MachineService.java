package com.machines.main.services;

import java.util.List;

import com.machines.main.dtos.Machine;

public interface MachineService {

	List<Machine> findAll();

	List<Machine> findByLocationName(String locationName);

	Machine findByName(String machineName);
}
