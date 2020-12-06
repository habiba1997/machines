package com.java.main.services;

import com.java.main.mappers.MachineOperationMapper;
import com.java.main.models.Machine;
import com.java.main.models.dtos.MachineDTO;
import com.java.main.repositories.MachineRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MachineService {

    @Autowired
    private MachineRepository repository;

    MachineOperationMapper mapper = Mappers.getMapper(MachineOperationMapper.class);

    @Cacheable("machinesList")
    public List<MachineDTO> getAllMachines() {
        return ( repository
                .findAll())
                .stream()
                .map(this::convertMachinesToDtos)
                .collect(Collectors.toList());
    }

    private MachineDTO convertMachinesToDtos(Machine machine) {
        return mapper.machineToDto(machine);
    }

}
