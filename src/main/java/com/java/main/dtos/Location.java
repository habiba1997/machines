package com.java.main.dtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.java.main.models.enums.LocationType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Location implements Serializable {

	private int key;
	private String name;
	private boolean temp;
	private LocationType type;

}
