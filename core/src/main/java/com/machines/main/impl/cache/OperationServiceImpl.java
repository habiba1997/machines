package com.machines.main.impl.cache;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.machines.main.cache.service.CacheService;
import com.machines.main.dtos.Operation;
import com.machines.main.error.NotFoundException;
import com.machines.main.mappers.OperationMapper;
import com.machines.main.models.enums.Status;
import com.machines.main.profile.CacheConstants;
import com.machines.main.repositories.OperationRepository;
import com.machines.main.services.OperationService;

@Service
public class OperationServiceImpl extends CacheService<String, Operation> implements OperationService {

	// here is the overridden method getTTl
	@Value("${cache.ttl.operation:}")
	private Duration timeToLive;

	@Autowired
	private OperationRepository repository;

	@Autowired
	private OperationMapper mapper;

	@Override
	public List<Operation> findByStatusIn(final List<Status> statusList) {
		Map<String, Operation> operationMap = this.fetchAndLoadAllCachedEntries();
		if (operationMap.isEmpty()) {
			return new ArrayList<>();
		}
		return operationMap.values().stream().filter(operation -> statusList.contains(operation.getStatus())).collect(Collectors.toList());
	}

	@Override
	public List<Operation> findByProductionOrderName(final String productionOrderName) {
		Map<String, Operation> operationMap = this.fetchAndLoadAllCachedEntries();
		if (operationMap.isEmpty()) {
			return new ArrayList<>();
		}
		return operationMap.values().stream().filter(operation -> operation.getProductionOrder().getName().equals(productionOrderName)).collect(Collectors.toList());
	}

	@Override
	public Operation findByName(final String operationName) {
		return Optional.ofNullable(this.fetchAndLoadAllCachedEntries().get(operationName))
				.orElseThrow(() -> new NotFoundException(String.format("Operation %s doesn't exist", operationName)));

	}

	@Override
	protected String getCacheName() {
		return CacheConstants.OPERATION;
	}

	@Override
	protected Duration getTimeToLive() {
		return timeToLive;
	}

	@Override
	protected List<Operation> findAllItemsFromDatabase() {
		return mapper.toModels(repository.findAll());
	}
}
