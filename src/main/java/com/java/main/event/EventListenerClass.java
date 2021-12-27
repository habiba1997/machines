package com.java.main.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import liquibase.pro.packaged.T;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EventListenerClass {

	@EventListener(condition = "#event.success")
	public void onApplicationEvent(final Event<T> event) {
		log.info("Event Successfully handled: " + event.getMessage());
	}
}
