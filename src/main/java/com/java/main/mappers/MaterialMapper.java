package com.java.main.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import com.java.main.dtos.Material;
import com.java.main.models.entity.MaterialEntity;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface MaterialMapper extends ModelMapper<MaterialEntity, Material> {

}
