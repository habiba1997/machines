package com.java.main.mappers;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import com.java.main.converters.ConverterEnumsClass;
import com.java.main.dtos.Machine;
import com.java.main.models.entity.MachineEntity;
import com.java.main.services.LocationService;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class MachineMapper implements ModelMapper<MachineEntity, Machine> {

	@Autowired
	private LocationService locationService;

	@Override
	@Mappings({
			@Mapping(target = "machineType", ignore = true),
			@Mapping(target = "location", ignore = true),
	})
	public abstract Machine toModel(MachineEntity machineEntity);

	// when using a builder, the @AfterMapping annotated method must have the builder as @MappingTarget annotated parameter so that the method
	// is able to modify the object going to be build. The build method is called when the @AfterMapping annotated method scope finishes.
	// MapStruct will not call the @AfterMapping annotated method if the real target is used as @MappingTarget annotated parameter.
	@AfterMapping
	public void fillMachineType(final MachineEntity machineEntity, @MappingTarget final Machine.MachineBuilder machine) {
		machine.machineType(ConverterEnumsClass.MACHINE_TYPE.toModel(List.of(machineEntity.isAssembly(), machineEntity.isPress())));
		if (machineEntity.getLocationKey() != null) {
			machine.location(locationService.findByKey(machineEntity.getLocationKey()));
		}
	}

//	@Override
//	@Mappings({
//			@Mapping(target = "assembly", ignore = true),
//			@Mapping(target = "press", ignore = true),
//			@Mapping(target = "id", ignore = true),
//			@Mapping(target = "locationEntity", source = "location"),
//	})
//	public abstract MachineEntity toEntity(Machine machine);
//
//	@AfterMapping
//	void mapMachineToMachineEntity(@MappingTarget final MachineEntity machineEntity, final Machine machine) {
//		if (machine.getMachineType().equals(MachineType.ASSEMBLY)) {
//			machineEntity.setAssembly(true);
//			machineEntity.setPress(false);
//		} else if (machine.getMachineType().equals(MachineType.PRESS)) {
//			machineEntity.setPress(true);
//			machineEntity.setAssembly(false);
//		}
//	}

}
