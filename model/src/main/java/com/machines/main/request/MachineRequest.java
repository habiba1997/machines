package com.machines.main.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.machines.main.models.enums.MachineType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MachineRequest {
	@NotNull
	private String name;
	private MachineType machineType;
	private String locationName;
}
