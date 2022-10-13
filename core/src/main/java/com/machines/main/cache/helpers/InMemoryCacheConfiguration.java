package com.machines.main.cache.helpers;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.machines.main.BaseCacheService;
import com.machines.main.cache.service.GoogleCacheService;

@Configuration
@Slf4j
@ConditionalOnProperty(value = "redis.cache.enabled", havingValue = "false", matchIfMissing = true)
public class InMemoryCacheConfiguration {
	@Bean
	public BaseCacheService baseCacheService() {
		log.debug("use in memory caching");
		return new GoogleCacheService();
	}
}
