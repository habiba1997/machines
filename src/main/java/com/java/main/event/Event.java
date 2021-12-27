package com.java.main.event;

import lombok.Data;

@Data
public class Event<T> {

	private T message;
	protected boolean success;

	public Event(final T message, final boolean success) {
		this.message = message;
		this.success = success;
	}

}
