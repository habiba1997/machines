package com.java.main.services;

import com.java.main.mappers.MachineOperationMapper;
import com.java.main.models.Status;
import com.java.main.models.dtos.MachineDTO;
import com.java.main.repositories.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Set;

@Service
public class MachineService {

    @Autowired
    private MachineRepository repository;

    @Autowired
    private MachineOperationMapper mapper;

    @Cacheable("machinesList")
    public Set<MachineDTO> getAllMachines() {
        return mapper.mapMachineSetToMachineDtoSet(repository.findAll());
    }

    @Cacheable("machineSpecifiedList")
    public Set<MachineDTO> getAllMachinesWithSpecificStatus() {
        Status[] statuses = new Status[]{Status.setup, Status.in_production, Status.over_production, Status.ending_production};
        return mapper.mapMachineSetToMachineDtoSet(repository.findMachinesWithSpecifiedProductionOrder(statuses));
    }

}
