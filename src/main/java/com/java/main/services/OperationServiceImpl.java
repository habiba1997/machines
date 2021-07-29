package com.java.main.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.java.main.error.ConflictException;
import com.java.main.error.NotFoundException;
import com.java.main.mappers.OperationMapper;
import com.java.main.models.Operation;
import com.java.main.models.Status;
import com.java.main.models.dtos.MachineDTO;
import com.java.main.models.dtos.OperationDTO;
import com.java.main.models.dtos.operation.OperationDTOWithMaterialMachine;
import com.java.main.repositories.MaterialRepository;
import com.java.main.repositories.OperationRepository;

@Service
public class OperationServiceImpl implements OperationService {

	@Autowired
	private OperationRepository operationRepository;

	@Autowired
	private OperationMapper mapper;

	@Autowired
	private MaterialRepository materialRepository;

	@Autowired
	private MachineService machineService;

	@Cacheable("operationSpecifiedProductionOrder")
	public Set<OperationDTO> getAllOperationsWithSetupAndInOverEndingProductionStatus() {
		Status[] statuses = new Status[] { Status.setup, Status.in_production, Status.over_production, Status.ending_production };
		return mapper.mapOperationsTOSetOfOperationDTOWithMaterialMachine(operationRepository.findByStatusIn(statuses));
	}

	public OperationDTOWithMaterialMachine getOperationById(int id) {
		return mapper.operationToOperationDTOWithMaterialMachine(findOperationById(id));
	}

	public Operation findOperationById(int id) {
		Operation operation = operationRepository.findOperationById(id);
		if (operation == null) {
			throw new NotFoundException("Operation required doesn't exist");
		}
		return operation;
	}

	@Override
	public Set<MachineDTO> getMachines(int id) {
		return machineService.getAllMachines();
	}

	public OperationDTOWithMaterialMachine getOperationIfWithSetupInOverEndingProductionOrder(int id) {
		Operation operation = findOperationById(id);
		Status status = operation.getStatus();
		if (status != Status.in_production && status != Status.over_production &&
				status != Status.ending_production && status != Status.setup) {
			throw new ConflictException("No production order step is running on this machine");
		}
		return mapper.operationToOperationDTOWithMaterialMachine(operation);
	}

	public OperationDTOWithMaterialMachine togglePercentageColor(int operationId) {
		Operation operation = findOperationById(operationId);
		Status status = operation.getStatus();
		if (status != Status.in_production && status != Status.over_production &&
				status != Status.ending_production && status != Status.setup) {
			throw new ConflictException("No production order step is running on this machine");
		}
		operation.getMaterial().setPercentageColor(!operation.getMaterial().isPercentageColor());
		materialRepository.save(operation.getMaterial());
		return mapper.operationToOperationDTOWithMaterialMachine(operation);
	}
}
