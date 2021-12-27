package com.java.main.models.dtos;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MachineDTO {
	private int id;
	private String name;
	private Set<OperationDTO> operations = new HashSet<>();

	public MachineDTO(final int id, final String name) {
		this.id = id;
		this.name = name;
	}

	public void addOperation(final OperationDTO operationDTO) {
		if (this.operations == null) {
			this.operations = new HashSet<>();
		}
		operations.add(operationDTO);
	}
}
