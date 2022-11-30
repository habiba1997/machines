package com.machines.main.impl.cache;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.machines.main.cache.service.CacheService;
import com.machines.main.dtos.Location;
import com.machines.main.error.NotFoundException;
import com.machines.main.mappers.LocationMapper;
import com.machines.main.profile.CacheConstants;
import com.machines.main.repositories.LocationRepository;
import com.machines.main.services.LocationService;

@Service
@Slf4j
public class LocationServiceImpl extends CacheService<Long, Location> implements LocationService {

	// here is the overridden method getTTl
	@Value("${cache.ttl.location:}")
	private Duration timeToLive;

	@Autowired
	private LocationRepository repository;

	@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
	@Autowired
	private LocationMapper mapper;

	@Override
	public List<Location> findAll() {
		return new ArrayList<>(this.fetchAndLoadAllCachedEntries().values());
	}

	@Override
	public Location findByName(final String locationName) {
		long startTime = System.currentTimeMillis();
		Map<Long, Location> locationMap = this.fetchAndLoadAllCachedEntries();
		long endTime = System.currentTimeMillis();
		log.debug("Fetch & load all location cache entries in {} milliseconds", (endTime - startTime));
		return locationMap.values().stream().filter(l -> l.getName().equals(locationName)).findFirst().orElse(null);
	}

	@Override
	public Long findLocationKey(final String locationName) {
		Map<Long, Location> locationMap = this.fetchAndLoadAllCachedEntries();
		return locationMap.values().stream().filter(location -> location.getName().equals(locationName)).findFirst()
				.map(location -> location.getKey())
				.orElseThrow(() -> new NotFoundException(String.format("Location %s doesn't exist", locationName)));
	}

	@Override
	public Optional<Location> findByKey(final long locationKey) {
		return Optional.ofNullable(this.getValueByKey(locationKey));
	}

	@Override
	protected String getCacheName() {
		return CacheConstants.LOCATION;
	}

	@Override
	protected Duration getTimeToLive() {
		return timeToLive;
	}

	@Override
	protected List<Location> findAllItemsFromDatabase() {
		return mapper.toModels(repository.findAll());
	}
}
