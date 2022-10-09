package com.java.main.interfaces.services;

import java.util.List;

import com.java.main.dtos.Operation;
import com.java.main.models.enums.Status;

public interface OperationService {

	List<Operation> findByStatusIn(List<Status> statuses);

	List<Operation> findByProductionOrderName(String productionOrderName);

	Operation findByName(String operationName);
}
