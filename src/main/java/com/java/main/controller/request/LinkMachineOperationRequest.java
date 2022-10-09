package com.java.main.controller.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LinkMachineOperationRequest {
	@NotNull
	private String operationName;
	@NotNull
	private String machineName;
}
