package com.java.main.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.java.main.models.enums.Status;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Operation {
	private String name;
	private Status status;
	private Material material;
	private Order productionOrder;

}
