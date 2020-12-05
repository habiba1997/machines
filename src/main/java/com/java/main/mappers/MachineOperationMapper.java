package com.java.main.mappers;

import com.java.main.models.Machine;
import com.java.main.models.Operation;
import com.java.main.models.dtos.MachineDTO;
import com.java.main.models.dtos.OperationDTO;
import org.mapstruct.Mapper;

@Mapper
public interface MachineOperationMapper {

    Operation dtoToOperation(OperationDTO operationDTO);

    OperationDTO operationToDto(Operation operation);

    Machine dtoToMachine(MachineDTO machineDto);

    MachineDTO machineToDto( Machine machine);


}
