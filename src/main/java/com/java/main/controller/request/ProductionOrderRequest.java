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
public class ProductionOrderRequest {

	@NotNull
	private String name;
	@NotNull
	private String materialName;
	@NotNull
	private MeasuredValueRequest plannedQuantity;

}
