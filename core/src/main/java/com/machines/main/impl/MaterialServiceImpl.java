package com.machines.main.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.machines.main.dtos.Material;
import com.machines.main.mappers.MaterialMapper;
import com.machines.main.repositories.MaterialRepository;
import com.machines.main.services.MaterialService;

@Service
public class MaterialServiceImpl implements MaterialService {

	@Autowired
	private MaterialRepository repository;

	@Autowired
	private MaterialMapper mapper;

	@Override
	public List<Material> findAll() {
		return mapper.toModels(repository.findAll());
	}

	@Override
	public Material findByName(final String materialName) {
		return mapper.toModel(repository.findByName(materialName));
	}

}
