package com.machines.main.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.machines.main.kafka.KafkaProducer;
import com.machines.main.kafka.Topic;
import com.machines.main.kafka.TopicName;
import com.machines.main.kafka.TopicObject;
import com.machines.main.kafka.objects.LinkUnLinkMachineOperationStatus;
import com.machines.main.kafka.objects.OperationChangeStatus;
import com.machines.main.response.Response;

@Aspect
@Component
public class KafkaAspect {

	@Autowired
	private KafkaProducer kafkaProducer;

	@Pointcut("@annotation(com.machines.main.aop.KafkaAnnotation)")
	public void produceKafkaTopicMethod() {
	}

	@Around("produceKafkaTopicMethod()")
	public Object produceKafka(final ProceedingJoinPoint pjp) throws Throwable {
		Response<Object> result = (Response<Object>) pjp.proceed();
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Method method = signature.getMethod();

		TopicName topicName = method.getAnnotation(KafkaAnnotation.class).topicName();
		TopicObject topicObject = createTopicObject(topicName, String.valueOf(pjp.getArgs()[0]), String.valueOf(pjp.getArgs()[1]));

		// this check is passed successfully
		if (result.isSuccess()) {
			kafkaProducer.sendMessage(new Topic(topicName, topicObject));
		}

		return result;
	}

	public TopicObject createTopicObject(final TopicName topicName, final String arg0, final String arg1) {
		switch (topicName) {
		case OPERATION_STATUS_CHANGE_TOPIC:
			return OperationChangeStatus.builder().operation(arg0).status(arg1).build();
		default:
			return LinkUnLinkMachineOperationStatus.builder().operation(arg0).machine(arg1).build();
		}
	}

}
