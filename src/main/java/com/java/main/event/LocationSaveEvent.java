package com.java.main.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import com.java.main.dtos.Location;

@Data
@Builder
@AllArgsConstructor
public class LocationSaveEvent extends Event {
	private Location location;
}
