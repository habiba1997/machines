package com.java.main.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.java.main.models.helpers.MeasuredValue;
import com.java.main.models.helpers.MesUnit;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Material {

	private String name;
	private MeasuredValue measuredValue;
	private MesUnit baseUnit;
}
