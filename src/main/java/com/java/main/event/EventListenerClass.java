package com.java.main.event;

import liquibase.pro.packaged.T;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class EventListenerClass  {

    @EventListener(condition = "#event.success")
    public void onApplicationEvent(Event<T> event) {
        System.out.println("Event Successfully handled: "+ event.getMessage());
    }
}
