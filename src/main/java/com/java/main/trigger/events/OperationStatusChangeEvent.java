package com.java.main.trigger.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import com.java.main.models.enums.Status;
import com.java.main.trigger.Event;

@Data
@Builder
@AllArgsConstructor
public class OperationStatusChangeEvent extends Event {
	private String operationName;
	private Status newStatus;
}
