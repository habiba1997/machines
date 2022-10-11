package com.machines.main.trigger;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.machines.main.ClearCacheService;

@Component
public class EventPublisher {

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	private ClearCacheService cacheService;

	public void publishEvents(final List<Event> events) {
		events.forEach(event -> applicationEventPublisher.publishEvent(event));
		cacheService.clearAllCache();
	}

}
