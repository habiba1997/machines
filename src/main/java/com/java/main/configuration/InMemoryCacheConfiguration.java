package com.java.main.configuration;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.java.main.cache.helpers.GoogleCacheService;
import com.java.main.cache.service.BaseCacheService;

@Configuration
@Slf4j
@ConditionalOnProperty(value = "redis.cache.enabled", havingValue = "false", matchIfMissing = true)
public class InMemoryCacheConfiguration {
	@Bean
	public BaseCacheService baseCacheService() {
		log.info("use in memory caching");
		return new GoogleCacheService();
	}
}
