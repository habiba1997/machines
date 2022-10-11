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
import com.machines.main.dtos.Machine;
import com.machines.main.error.NotFoundException;
import com.machines.main.mappers.MachineMapper;
import com.machines.main.profile.CacheConstants;
import com.machines.main.repositories.MachineRepository;
import com.machines.main.services.MachineService;

@Service
public class MachineServiceImpl extends CacheService<String, Machine> implements MachineService {

	@Value("${cache.ttl.operation:}")
	private Duration timeToLive;

	@Autowired
	private MachineRepository repository;

	@Autowired
	private MachineMapper mapper;

	@Override
	public List<Machine> findAll() {
		return new ArrayList<>(this.fetchAndLoadAllCachedEntries().values());
	}

	@Override
	public List<Machine> findByLocationName(final String locationName) {
		Map<String, Machine> machineMap = this.fetchAndLoadAllCachedEntries();
		if (machineMap.isEmpty()) {
			return new ArrayList<>();
		}
		return machineMap.values().stream().filter(machine -> machine.getLocation().map(location -> location.getName().equals(locationName)).orElse(false)).collect(Collectors.toList());

	}

	@Override
	public Machine findByName(final String machineName) {
		return Optional.ofNullable(this.fetchAndLoadAllCachedEntries().get(machineName))
				.orElseThrow(() -> new NotFoundException(String.format("Machine %s doesn't exist", machineName)));

	}

	@Override
	protected String getCacheName() {
		return CacheConstants.MACHINE;
	}

	@Override
	protected Duration getTimeToLive() {
		return timeToLive;
	}

	@Override
	protected List<Machine> findAllItemsFromDatabase() {
		return mapper.toModels(repository.findAll());
	}
}
