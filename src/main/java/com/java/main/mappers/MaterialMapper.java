package com.java.main.mappers;

import java.util.Set;

import org.mapstruct.Mapper;

import com.java.main.models.Material;
import com.java.main.models.dtos.MaterialDTO;

@Mapper(componentModel = "spring")
public interface MaterialMapper {

    MaterialDTO materialToDto(Material material);

    Set<MaterialDTO> mapMaterialSetToMaterialDtoSet(Set<Material> materials);
}
