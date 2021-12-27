package com.java.main.mappers;

import java.util.Set;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import com.java.main.models.Machine;
import com.java.main.models.Material;
import com.java.main.models.MeasuredValue;
import com.java.main.models.Operation;
import com.java.main.models.dtos.MeasuredValueDTO;
import com.java.main.models.dtos.OperationDTO;
import com.java.main.models.dtos.operation.MachineDTONoOperation;
import com.java.main.models.dtos.operation.MaterialDTONoOperation;
import com.java.main.models.dtos.operation.OperationDTOWithMaterialMachine;

@Mapper(componentModel = "spring")
public interface OperationMapper {

	MaterialDTONoOperation materialToMaterialDTONoOperation(Material material);

	MeasuredValueDTO measuredValueToDto(MeasuredValue measuredValue);

	MachineDTONoOperation machineToMachineDTONoOperation(Machine machine);

	OperationDTOWithMaterialMachine operationToOperationDTOWithMaterialMachine(Operation operation);

	OperationDTO operationToDto(Operation operation);

	@IterableMapping(qualifiedByName = "operationToDto")
	Set<OperationDTO> mapOperationsTOSetOfOperationDTOWithMaterialMachine(Set<Operation> operations);
}
