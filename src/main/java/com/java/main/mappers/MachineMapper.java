package com.java.main.mappers;

import com.java.main.models.Machine;
import com.java.main.models.Operation;
import com.java.main.models.dtos.MachineDTO;
import com.java.main.models.dtos.OperationDTO;
import org.mapstruct.*;

import java.util.Set;


@Mapper(componentModel = "spring")
public interface MachineMapper {

    MachineDTO machineToDto( Machine machine);

    OperationDTO operationToDto(Operation operation);

    Set<MachineDTO> mapMachineSetToMachineDtoSet(Set<Machine> machineSet);
}
