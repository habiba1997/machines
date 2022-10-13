package com.machines.main.kafka;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;

@Configuration
@ConditionalOnProperty(name = "kafka.enabled", havingValue = "true", matchIfMissing = false)
public class KafkaTopicConfiguration {

	public static final int NUM_PARTITIONS = 3;
	public static final short REPLICATION_FACTOR = (short) 1;
	private final SingletonBeanRegistry beanRegistry;

	@Autowired
	private GenericApplicationContext context;

	public KafkaTopicConfiguration(final GenericApplicationContext context) {
		this.beanRegistry = context.getBeanFactory();
	}

	@PostConstruct
	public void createTopics() {
		TopicName.getTopics().stream()
				.forEach(topic -> {
					NewTopic newTopic = new NewTopic(topic, NUM_PARTITIONS, REPLICATION_FACTOR);
					beanRegistry.registerSingleton("topic-" + topic, newTopic);

				});
	}
}
