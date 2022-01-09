package com.java.main.models.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Status {
	NONE,
	PLANNED,
	SETUP,
	IN_PRODUCTION,
	SUSPENDED,
	CLOSED;
	
	public static List<Status> getEnum(final List<String> statusList) {
		List<Status> statuses = new ArrayList<>();
		for (String statusString: statusList) {
			Arrays.stream(Status.values()).forEach(status -> { 
				if (status.toString().equals(statusString)) {
					statuses.add(status);
				}
			});
		}
		return statuses;
	}
}
