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
public class MaterialDTO {

	private int id;
	private String name;
	private Set<OperationDTO> operations = new HashSet<>();
	private MeasuredValueDTO measuredValue;
	private boolean percentageColor;

	public MaterialDTO(final int id, final String name) {
		this.id = id;
		this.name = name;
	}

	public void addOperations(final OperationDTO operationDTO) {
		operations.add(operationDTO);
	}
}
