package com.java.main.models.dtos.operation;

import com.java.main.models.dtos.MeasuredValueDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialDTONoOperation {

	private int id;
	private String name;
	private MeasuredValueDTO measuredValue;
	private boolean percentageColor;

}
