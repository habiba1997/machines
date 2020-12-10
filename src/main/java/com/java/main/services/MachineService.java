package com.java.main.services;

import com.java.main.mappers.MachineMapper;
import com.java.main.models.Status;
import com.java.main.models.dtos.MachineDTO;
import com.java.main.repositories.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MachineService {

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
        Status[] statuses = new Status[]{Status.setup, Status.in_production, Status.over_production, Status.ending_production};
        return mapper.mapMachineSetToMachineDtoSet(repository.findMachinesWithSpecificProductionOrder(statuses));
    }

}
