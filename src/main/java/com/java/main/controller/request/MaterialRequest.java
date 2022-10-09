package com.java.main.controller.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.java.main.models.helpers.MesUnit;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaterialRequest {

	@NotNull
	private String name;
	@NotNull
	private MeasuredValueRequest measuredValue;

	private MesUnit baseUnit;
}
