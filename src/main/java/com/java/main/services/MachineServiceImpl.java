package com.java.main.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.java.main.mappers.MachineMapper;
import com.java.main.models.Status;
import com.java.main.models.dtos.MachineDTO;
import com.java.main.repositories.MachineRepository;

@Service
public class MachineServiceImpl implements MachineService {

	@Autowired
	private MachineRepository repository;

	@Autowired
	private MachineMapper mapper;

	@Cacheable("machinesList")
	public Set<MachineDTO> getAllMachines() {
		return mapper.mapMachineSetToMachineDtoSet(repository.findAll());
	}

	@Cacheable("machineSpecifiedProductionOrder")
	public Set<MachineDTO> getAllMachinesWithSetupAndInOverEndingProductionStatus() {
		Status[] statuses = new Status[] { Status.SETUP, Status.IN_PRODUCTION, Status.OVER_PRODUCTION, Status.ENDING_PRODUCTION };
		return mapper.mapMachineSetToMachineDtoSet(repository.findMachinesWithSpecificProductionOrder(statuses));
	}

}
