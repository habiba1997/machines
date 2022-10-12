package com.machines.main.kafka.objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.machines.main.kafka.TopicObject;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperationChangeStatus extends TopicObject {
	private String operation;
	private String status;
}
