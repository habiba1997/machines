package com.java.main.trigger.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import com.java.main.dtos.Location;
import com.java.main.trigger.Event;

@Data
@Builder
@AllArgsConstructor
public class LocationCreateEvent extends Event {
	private Location location;
}
