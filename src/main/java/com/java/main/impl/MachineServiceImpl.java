package com.java.main.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.main.dtos.Machine;
import com.java.main.error.NotFoundException;
import com.java.main.interfaces.services.MachineService;
import com.java.main.mappers.MachineMapper;
import com.java.main.repositories.MachineRepository;

@Service
public class MachineServiceImpl implements MachineService {

	@Autowired
	private MachineRepository machineRepository;

	@Autowired
	private MachineMapper machineMapper;

	@Override
	public List<Machine> findAll() {
		return machineMapper.toModels(machineRepository.findAll());
	}

	@Override
	public List<Machine> findByLocationName(final String locationName) {
		return machineMapper.toModels(machineRepository.findMachinesByLocationName(locationName));
	}

	@Override
	public Machine findByName(final String machineName) {
		return machineMapper.toModel(machineRepository.findByName(machineName)
				.orElseThrow(() -> new NotFoundException(String.format("Machine %s doesn't exist", machineName))));

	}
}
