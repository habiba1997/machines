package com.machines.main.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.machines.main.models.enums.Status;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperationRequest {

	@NotNull
	private String name;
	@NotNull
	private Status status;
	@NotNull
	private String materialName;
	@NotNull
	private String productionOrderName;
}
