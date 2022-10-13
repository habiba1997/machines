package com.machines.main.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.machines.main.kafka.TopicName;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface KafkaAnnotation {
	TopicName topicName() default TopicName.OPERATION_STATUS_CHANGE_TOPIC;
}