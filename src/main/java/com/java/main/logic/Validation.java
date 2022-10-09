package com.java.main.logic;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import com.java.main.trigger.Event;

@Data
@Builder
@AllArgsConstructor
public class Validation<B> {
	private boolean success = true;
	@Singular
	private List<Message> messages;
	private B body;
	private List<Event> events;

	public Validation() {
		this.success = true;
	}

	public void addSuccessMessage(final String message, final String... params) {
		if (this.messages == null) {
			this.messages = new ArrayList<>();
		}
		this.messages.add(new Message(String.format(message, params), MessageType.SUCCESS));
	}

	public void addFailMessage(final String message, final String... params) {
		if (this.messages == null) {
			this.messages = new ArrayList<>();
		}
		this.messages.add(new Message(String.format(message, params), MessageType.ERROR));
		success = false;
	}

	public void addEvent(final Event event) {
		if (this.events == null) {
			this.events = new ArrayList<>();
		}
		this.events.add(event);
	}

}
