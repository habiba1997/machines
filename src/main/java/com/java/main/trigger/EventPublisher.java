package com.java.main.trigger;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.java.main.impl.cache.CacheService;

@Component
public class EventPublisher {

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	private CacheService cacheService;

	public void publishEvents(final List<Event> events) {
		events.forEach(event -> applicationEventPublisher.publishEvent(event));
//		cacheService.clearAllCache();
	}

}
