package com.machines.main.services;

import java.util.List;

import com.machines.main.dtos.Operation;
import com.machines.main.models.enums.Status;

public interface OperationService {

	List<Operation> findByStatusIn(List<Status> statuses);

	List<Operation> findByProductionOrderName(String productionOrderName);

	Operation findByName(String operationName);
}
