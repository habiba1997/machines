package com.java.main.converters;

import java.util.List;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import com.java.main.models.enums.MachineType;
import com.java.main.models.enums.Status;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConverterEnumsClass {
	public static final ModelEntityConverter<Status, String> OPERATION_STATUS = ModelEntityConverter.<Status, String>build()
			.and(Status.PLANNED, "planned")
			.and(Status.SETUP, "setup")
			.and(Status.SUSPENDED, "suspended")
			.and(Status.IN_PRODUCTION, "in_production")
			.and(Status.CLOSED, "closed")
			.defaultDomain(Status.NONE);

	public static final ModelEntityConverter<MachineType, List<Boolean>> MACHINE_TYPE = ModelEntityConverter.<MachineType, List<Boolean>>build()
			.and(MachineType.ASSEMBLY, List.of(true, false))
			.and(MachineType.PRESS, List.of(false, true))
			.defaultDomain(MachineType.NONE);
}
