package com.java.main.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.java.main.services.CacheService;

@Component
public class EventPublisher {

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	private CacheService cacheService;

	public void publish(final Event event) {
		applicationEventPublisher.publishEvent(event);
		cacheService.clearAllCache();
	}

}
