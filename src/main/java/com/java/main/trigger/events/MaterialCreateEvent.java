package com.java.main.trigger.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import com.java.main.dtos.Material;
import com.java.main.trigger.Event;

@Data
@Builder
@AllArgsConstructor
public class MaterialCreateEvent extends Event {
	private Material material;
}
