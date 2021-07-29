package com.java.main.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.java.main.mappers.MaterialMapper;
import com.java.main.models.dtos.MaterialDTO;
import com.java.main.repositories.MaterialRepository;

@Service
public class MaterialServiceImpl implements MaterialService {

	@Autowired
	private MaterialRepository repository;

	@Autowired
	private MaterialMapper mapper;

	@Cacheable("materialsList")
	public Set<MaterialDTO> getAllMaterials() {
		return mapper.mapMaterialSetToMaterialDtoSet(repository.findAll());
	}

}
