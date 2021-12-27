package com.java.main.models.dtos.operation;

import com.java.main.models.Status;

import lombok.*;

@Data
@Builder
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class OperationDTOWithMaterialMachine {

	private int id;
	private String name;
	private MachineDTONoOperation machine;
	private MaterialDTONoOperation material;
	private Status status;

}
