package com.java.main.services;

import com.java.main.mappers.MaterialMapper;
import com.java.main.models.dtos.MaterialDTO;
import com.java.main.repositories.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class MaterialService {

    @Autowired
    private MaterialRepository repository;

    @Autowired
    private MaterialMapper mapper;

    @Cacheable("materialsList")
    public Set<MaterialDTO> getAllMaterials() {
        return mapper.mapMaterialSetToMaterialDtoSet(repository.findAll());
    }

}