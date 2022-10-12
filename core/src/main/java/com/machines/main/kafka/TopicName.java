package com.machines.main.kafka;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum TopicName {

	OPERATION_STATUS_CHANGE_TOPIC("operation-status-changed-topic"),
	LINK_OPERATION_TO_MACHINE_TOPIC("link-operation-to-machine-topic"),
	UNLINK_OPERATION_TO_MACHINE_TOPIC("unlink-operation-to-machine-topic");

	private String value;

	public String getValue() {
		return value;
	}

	TopicName(final String value) {
		this.value = value;
	}

	public static List<String> getTopics() {
		return Arrays.stream(TopicName.class.getEnumConstants()).map(n -> n.value).collect(Collectors.toList());
	}
}
