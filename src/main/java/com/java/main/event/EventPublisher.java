package com.java.main.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishCustomEvent(final String message,boolean bool) {
        System.out.println("Publishing custom event ");
        Event event = new Event(this,message,bool);
        applicationEventPublisher.publishEvent(event);
    }

}
