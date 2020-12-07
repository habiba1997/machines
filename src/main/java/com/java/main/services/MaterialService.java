package com.java.main.services;

import com.java.main.mappers.MachineOperationMapper;
import com.java.main.mappers.MaterialOperationMapper;
import com.java.main.models.dtos.MaterialDTO;
import com.java.main.repositories.MaterialRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class MaterialService {

    @Autowired
    private MaterialRepository repository;

    @Autowired
    private MaterialOperationMapper mapper;

    @Cacheable("materialsList")
    public Set<MaterialDTO> getAllMaterials() {
        return mapper.mapMaterialListToMaterialDtoList(repository.findAll());
    }


}
