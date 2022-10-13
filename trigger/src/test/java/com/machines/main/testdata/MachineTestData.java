package com.machines.main.testdata;

import com.machines.main.dtos.Machine;
import com.machines.main.models.entity.MachineEntity;
import com.machines.main.models.enums.MachineType;

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
				.key(2934014L)
				.name(MACHINE_NAME)
				.assembly(true)
				.press(false)
				.locationKey(LocationTestData.LOCATION_KEY)
				.build();
	}
}
