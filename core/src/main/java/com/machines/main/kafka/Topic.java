package com.machines.main.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Topic<T extends TopicObject> {

	private TopicName name;
	private T object;

}