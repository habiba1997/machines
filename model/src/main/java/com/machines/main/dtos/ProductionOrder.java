package com.machines.main.dtos;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.machines.main.models.helpers.MeasuredValue;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductionOrder implements Serializable {
	private String name;
	private ZonedDateTime startDate;
	private Material material;
	private MeasuredValue plannedQuantity;
	private List<Operation> operationList;
}
