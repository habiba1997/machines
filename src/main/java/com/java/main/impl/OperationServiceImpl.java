package com.java.main.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.main.converters.ConverterEnumsClass;
import com.java.main.dtos.Operation;
import com.java.main.error.NotFoundException;
import com.java.main.interfaces.services.OperationService;
import com.java.main.mappers.OperationMapper;
import com.java.main.models.enums.Status;
import com.java.main.repositories.OperationRepository;

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
