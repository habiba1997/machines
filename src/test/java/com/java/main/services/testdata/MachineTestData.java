package com.java.main.services.testdata;

import com.java.main.dtos.Machine;
import com.java.main.models.entity.MachineEntity;
import com.java.main.models.enums.MachineType;

public class MachineTestData {

	public static final String MACHINE_NAME = "machine1";

	public static Machine generateMachine() {
		return Machine.builder()
				.name(MACHINE_NAME)
				.machineType(MachineType.ASSEMBLY)
				.location(LocationTestData.generateLocation(true))
				.build();
	}

	public static MachineEntity generateMachineEntity() {
		return MachineEntity.builder()
				.id(2934014)
				.name(MACHINE_NAME)
				.assembly(true)
				.press(false)
				.locationEntity(LocationTestData.generateLocationEntity())
				.build();
	}
}
