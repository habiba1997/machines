package com.machines.main.trigger.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import com.machines.main.trigger.Event;

@Data
@Builder
@AllArgsConstructor
public class MachineOperationUnLinkEvent extends Event {
	private String operationName;
}
