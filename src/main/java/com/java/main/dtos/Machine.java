package com.java.main.dtos;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.java.main.models.enums.MachineType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Machine {
	private String name;
	private MachineType machineType;
	private Optional<Location> location;

}
