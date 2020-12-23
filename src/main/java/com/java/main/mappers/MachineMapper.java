package com.java.main.mappers;

import com.java.main.models.Machine;
import com.java.main.models.Operation;
import com.java.main.models.dtos.MachineDTO;
import com.java.main.models.dtos.OperationDTO;
import org.mapstruct.*;

import java.util.Set;


@Mapper(uses = {OperationMapper.class}, componentModel = "spring")
public interface MachineMapper {

    MachineDTO machineToDto( Machine machine);

    Set<MachineDTO> mapMachineSetToMachineDtoSet(Set<Machine> machineSet);
}
