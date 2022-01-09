package com.java.main.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.main.dtos.Machine;
import com.java.main.mappers.MachineMapper;
import com.java.main.repositories.MachineRepository;
import com.java.main.services.MachineService;

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
}
