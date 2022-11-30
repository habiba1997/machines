package com.machines.main.impl;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.machines.main.cache.service.CacheService;
import com.machines.main.converters.ConverterEnumsClass;
import com.machines.main.dtos.Machine;
import com.machines.main.dtos.MachineOperation;
import com.machines.main.error.NotFoundException;
import com.machines.main.mappers.LocationMapper;
import com.machines.main.mappers.OperationMapper;
import com.machines.main.models.entity.MachineOperationEntity;
import com.machines.main.models.entity.OperationEntity;
import com.machines.main.profile.CacheConstants;
import com.machines.main.repositories.MachineOperationRepository;
import com.machines.main.repositories.OperationRepository;
import com.machines.main.services.MachineOperationService;

@Service
public class MachineOperationServiceImpl extends CacheService<String, MachineOperation> implements MachineOperationService {

	@Value("${cache.ttl.machine-operation:}")
	private Duration timeToLive;

	@Autowired
	private MachineOperationRepository repository;

	@Autowired
	private OperationRepository operationRepository;

	@Autowired
	private LocationMapper locationMapper;

	@Autowired
	private OperationMapper operationMapper;

	@Override
	public List<MachineOperation> findAll() {
		List<MachineOperationEntity> machineOperationEntities = repository.findAll();
		if (machineOperationEntities == null || machineOperationEntities.isEmpty()) {
			return new ArrayList<>();
		}

		return machineOperationEntities.stream().filter(moe -> moe.getOperationEntity() != null).map(moe -> {
			Machine machine = Machine.builder()
					.name(moe.getMachineName())
					.machineType(ConverterEnumsClass.MACHINE_TYPE.toModel(List.of(moe.isAssembly(), moe.isPress())))
					.location(locationMapper.toModel(moe.getLocationEntity()))
					.build();
			return new MachineOperation(machine, operationMapper.toModel(moe.getOperationEntity()));
		}).collect(Collectors.toList());
	}

	@Override
	public MachineOperation findByName(final String machineName) {
		MachineOperationEntity moe = repository.findByMachineName(machineName);
		if (moe == null) {
			return null;
		}
		Machine machine = Machine.builder()
				.name(moe.getMachineName())
				.machineType(ConverterEnumsClass.MACHINE_TYPE.toModel(List.of(moe.isAssembly(), moe.isPress())))
				.location(locationMapper.toModel(moe.getLocationEntity()))
				.build();
		return new MachineOperation(machine, operationMapper.toModel(moe.getOperationEntity()));
	}

	@Override
	public boolean isOperationLinkedToOtherMachine(final String operationName) {
		OperationEntity operationEntity = operationRepository.findByName(operationName)
				.orElseThrow(() -> new NotFoundException(String.format("Operation %s doesn't exist", operationName)));
		return Optional.ofNullable(operationEntity.getMachineEntity()).isPresent();

	}

	@Override
	public MachineOperation findByNameOrThrow(final String machineName) {
		MachineOperationEntity moe = repository.findByMachineName(machineName);
		if (moe == null || moe.getOperationEntity() == null) {
			throw new NotFoundException(String.format("Machine %s is not connected to any operation", machineName));
		}
		Machine machine = Machine.builder()
				.name(moe.getMachineName())
				.machineType(ConverterEnumsClass.MACHINE_TYPE.toModel(List.of(moe.isAssembly(), moe.isPress())))
				.location(locationMapper.toModel(moe.getLocationEntity()))
				.build();
		return new MachineOperation(machine, operationMapper.toModel(moe.getOperationEntity()));

	}

	@Override
	protected String getCacheName() {
		return CacheConstants.MACHINE_OPERATION;
	}

	@Override
	protected Duration getTimeToLive() {
		return timeToLive;
	}

	@Override
	protected List<MachineOperation> findAllItemsFromDatabase() {
		return findAll();
	}
}
