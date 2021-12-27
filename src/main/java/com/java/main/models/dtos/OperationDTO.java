package com.java.main.models.dtos;

import com.java.main.models.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperationDTO {
	private int id;
	private String name;
	private Status status;
}
