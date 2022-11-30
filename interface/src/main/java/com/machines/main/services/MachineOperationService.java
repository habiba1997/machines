package com.machines.main.services;

import java.util.List;

import com.machines.main.dtos.MachineOperation;

public interface MachineOperationService {

	List<MachineOperation> findAll();

	MachineOperation findByName(String machineName);

	MachineOperation findByNameOrThrow(String machineName);

	boolean isOperationLinkedToOtherMachine(String operationName);
}
