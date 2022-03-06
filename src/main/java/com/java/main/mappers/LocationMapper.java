package com.java.main.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import com.java.main.dtos.Location;
import com.java.main.models.entity.LocationEntity;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface LocationMapper extends ModelMapper<LocationEntity, Location>, EntityMapper<LocationEntity, Location> {

}
