package com.machines.main.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.machines.main.converters.ConverterEnumsClass;
import com.machines.main.dtos.Operation;
import com.machines.main.error.NotFoundException;
import com.machines.main.mappers.OperationMapper;
import com.machines.main.models.enums.Status;
import com.machines.main.repositories.OperationRepository;
import com.machines.main.services.OperationService;

@Service
public class OperationServiceImpl implements OperationService {

	@Autowired
	private OperationRepository repository;

	@Autowired
	private OperationMapper mapper;

	@Override
	public List<Operation> findByStatusIn(final List<Status> statusList) {
		List<String> statuses = ConverterEnumsClass.OPERATION_STATUS.toEntities(statusList);
		return mapper.toModels(repository.findByStatusIn(statuses));
	}

	@Override
	public List<Operation> findByProductionOrderName(final String productionOrderName) {
		return mapper.toModels(repository.findByProductionOrderName(productionOrderName));
	}

	@Override
	public Operation findByName(final String operationName) {
		return mapper.toModel(repository.findByName(operationName)
				.orElseThrow(() -> new NotFoundException(String.format("Operation %s doesn't exist", operationName))));

	}

}
