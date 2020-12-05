package com.java.main.services;

import com.java.main.mappers.MaterialOperationMapper;
import com.java.main.models.Material;
import com.java.main.models.dtos.MaterialDTO;
import com.java.main.repositories.Material_Repository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
public class Material_Service {

    @Autowired
    private Material_Repository repository;

    MaterialOperationMapper mapper = Mappers.getMapper(MaterialOperationMapper.class);

    @Cacheable("materialsList")
    public Set<MaterialDTO> getAllMaterials() {
        return mapper.mapMaterialListToMaterialDtoList(repository.findAll());
    }


}
