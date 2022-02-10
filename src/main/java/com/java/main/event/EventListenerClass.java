package com.java.main.event;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventListenerClass<T> {

	@EventListener(condition = "#event.success")
	public void onApplicationEvent(final Event<T> event) {
		log.debug("Event Successfully handled: " + event.getMessage());
	}
}
