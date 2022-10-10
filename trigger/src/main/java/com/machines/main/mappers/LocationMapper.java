package com.machines.main.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import com.machines.main.dtos.Location;
import com.machines.main.models.entity.LocationEntity;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface LocationMapper extends ModelMapper<LocationEntity, Location>, EntityMapper<LocationEntity, Location> {

}
