package com.java.main.trigger.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import com.java.main.dtos.ProductionOrder;
import com.java.main.trigger.Event;

@Data
@Builder
@AllArgsConstructor
public class ProductionOrderCreateEvent extends Event {
	private ProductionOrder productionOrder;
}
