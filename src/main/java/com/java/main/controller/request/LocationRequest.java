package com.java.main.controller.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationRequest {
	private String name;
	private boolean temp;
	private String type;
}
