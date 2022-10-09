package com.java.main.controller.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.java.main.models.enums.Status;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangeOperationStatusRequest {
	@NotNull
	private String operationName;
	@NotNull
	private Status operationNewStatus;
}
