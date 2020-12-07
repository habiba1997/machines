package com.java.main.mappers;

import com.java.main.models.Machine;
import com.java.main.models.Material;
import com.java.main.models.Operation;
import com.java.main.models.dtos.MachineDTO;
import com.java.main.models.dtos.MaterialDTO;
import com.java.main.models.dtos.OperationDTO;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface MachineOperationMapper {

    Operation dtoToOperation(OperationDTO operationDTO);

    OperationDTO operationToDto(Operation operation);

    Machine dtoToMachine(MachineDTO machineDto);

    MachineDTO machineToDto( Machine machine);

    Set<MaterialDTO> mapMaterialListToMaterialDtoList(Set<Material> all);
}
