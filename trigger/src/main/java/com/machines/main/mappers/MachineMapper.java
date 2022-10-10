package com.machines.main.mappers;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import com.machines.main.converters.ConverterEnumsClass;
import com.machines.main.dtos.Machine;
import com.machines.main.models.entity.MachineEntity;
import com.machines.main.models.enums.MachineType;
import com.machines.main.services.LocationService;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class MachineMapper implements ModelMapper<MachineEntity, Machine>, EntityMapper<MachineEntity, Machine> {

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

	@Override
	public MachineEntity toEntity(final Machine machine) {
		MachineEntity machineEntity = new MachineEntity();
		machineEntity.setName(machine.getName());

		if (machine.getMachineType().equals(MachineType.ASSEMBLY)) {
			machineEntity.setAssembly(true);
			machineEntity.setPress(false);
		} else if (machine.getMachineType().equals(MachineType.PRESS)) {
			machineEntity.setPress(true);
			machineEntity.setAssembly(false);
		}
		if (machine.getLocation().isPresent()) {
			machineEntity.setLocationKey(locationService.findLocationKey(machine.getLocation().get().getName()));
		}
		return machineEntity;
	}

}
