package com.java.main.services;

import java.util.Set;

import com.java.main.models.Operation;
import com.java.main.models.dtos.MachineDTO;
import com.java.main.models.dtos.OperationDTO;
import com.java.main.models.dtos.operation.OperationDTOWithMaterialMachine;

public interface OperationService {

	Set<OperationDTO> getAllOperationsWithSetupAndInOverEndingProductionStatus();

	OperationDTOWithMaterialMachine getOperationById(int id);

	Operation findOperationById(int id);

	OperationDTOWithMaterialMachine getOperationIfWithSetupInOverEndingProductionOrder(int id);

	OperationDTOWithMaterialMachine togglePercentageColor(int operationId);

	Set<MachineDTO> getMachines(int id);
}
