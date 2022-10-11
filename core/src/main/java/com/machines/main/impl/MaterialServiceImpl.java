package com.machines.main.impl;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.machines.main.cache.service.CacheService;
import com.machines.main.dtos.Material;
import com.machines.main.error.NotFoundException;
import com.machines.main.mappers.MaterialMapper;
import com.machines.main.profile.CacheConstants;
import com.machines.main.repositories.MaterialRepository;
import com.machines.main.services.MaterialService;

@Service
public class MaterialServiceImpl extends CacheService<String, Material> implements MaterialService {

	// here is the overridden method getTTl
	@Value("${cache.ttl.material:}")
	private Duration timeToLive;
	@Autowired
	private MaterialRepository repository;

	@Autowired
	private MaterialMapper mapper;

	@Override
	public List<Material> findAll() {
		return new ArrayList<>(this.fetchAndLoadAllCachedEntries().values());
	}

	@Override
	public Material findByName(final String materialName) {
		return Optional.ofNullable(this.fetchAndLoadAllCachedEntries().get(materialName))
				.orElseThrow(() -> new NotFoundException(String.format("Material %s doesn't exist", materialName)));
	}

	@Override
	protected String getCacheName() {
		return CacheConstants.MATERIAL;
	}

	@Override
	protected Duration getTimeToLive() {
		return timeToLive;
	}

	@Override
	protected List<Material> findAllItemsFromDatabase() {
		return mapper.toModels(repository.findAll());
	}

}
