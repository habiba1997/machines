package com.java.main.configuration;

import lombok.extern.slf4j.Slf4j;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.SerializationCodec;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;

import com.java.main.cache.helpers.RedisCacheService;
import com.java.main.cache.service.BaseCacheService;

@Slf4j
@Configuration
@ConditionalOnProperty(name = "redis.cache.enabled", havingValue = "true")
public class RedisCacheConfiguration {

	// Unfortunately, Redis does not include support for either the Java programming language in general or
	// Java application frameworks like Spring, out of the box.
	// The good news is that you can use a third-party Redis Java client such as Redisson to implement a Spring cache.

	@Value("${spring.redisson.address:}")
	private String redisAddress;

	@Bean
	public RedissonConnectionFactory redissonConnectionFactory(final RedissonClient redisson) {
		return new RedissonConnectionFactory(redisson);
	}

	private String getRadissonAddress() {
		return "redis://" + redisAddress;
	}

	@Bean
	@Primary
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(redissonConnectionFactory(redisson()));
		return template;
	}

	@Bean
	public BaseCacheService baseCacheService() {
		log.info("use in redis caching");
		return new RedisCacheService();
	}

	@Bean
	public RedissonClient redisson() {
		Config config = new Config();
		// we specified to Redisson that we want to connect to a single node instance of Redis.
		// To do this we used the Config object's useSingleServer method.
		// This returns a reference to a SingleServerConfig object.
		config.useSingleServer().setAddress(getRadissonAddress());

		// Sending commands to a Redis Server:
		// A client sends the Redis server a RESP Array consisting of just Bulk Strings.
		// A Redis server replies to clients sending any valid RESP data type as reply.

		// Redis clients communicate with the Redis server using a protocol called RESP (REdis Serialization Protocol).
		// While the protocol was designed specifically for Redis, it can be used for other client-server software projects.

		// RESP is a compromise between the following things:
		// Simple to implement.
		// Fast to parse.
		// Human readable.

		// RESP can serialize different data types like integers, strings, arrays. There is also a specific type for errors.
		// Requests are sent from the client to the Redis server as arrays of strings representing the arguments of the command to execute.
		// Redis replies with a command-specific data type.

		// RESP is binary-safe and does not require processing of bulk data transferred from one process to another, because it uses prefixed-length to transfer bulk data.
		// Note: the protocol outlined here is only used for client-server communication.
		// Redis Cluster uses a different binary protocol in order to exchange messages between nodes.

		// Networking layer
		// A client connects to a Redis server creating a TCP connection to the port 6379.
		// While RESP is technically non-TCP specific, in the context of Redis the protocol is only used with TCP connections.

		// default transport mode is NIO ( don't need to state it) => NIO Transport is very similar to the regular TCP transport.
		// The difference is that it is implemented using NIO API which can help with performance and scalability.
		// NIO is a server side transport option only. Trying to use it on the client side will instantiate the regular TCP transport.
		config.setTransportMode(TransportMode.NIO);

		// Redis data codec. Used during read and write Redis data(Data serialization)
		// org.redisson.codec.SerializationCodec JDK Serialization binary codec (Android compatible)
		// different serialization exist
		config.setCodec(new SerializationCodec());

		return Redisson.create(config);
	}

}
