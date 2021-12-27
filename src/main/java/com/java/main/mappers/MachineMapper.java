package com.java.main.mappers;

import java.util.Set;

import org.mapstruct.Mapper;

import com.java.main.models.Machine;
import com.java.main.models.dtos.MachineDTO;

@Mapper(uses = { OperationMapper.class }, componentModel = "spring")
public interface MachineMapper {

	MachineDTO machineToDto(Machine machine);

	Set<MachineDTO> mapMachineSetToMachineDtoSet(Set<Machine> machineSet);
}
