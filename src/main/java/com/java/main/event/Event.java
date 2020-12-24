package com.java.main.event;


import liquibase.pro.packaged.T;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationEvent;

@Data
public class Event<T> extends ApplicationEvent {

    private T message;
    protected boolean success;

    public Event(Object obj , T message, boolean success) {
        super(obj);
        this.message = message;
        this.success = success;
    }


}
