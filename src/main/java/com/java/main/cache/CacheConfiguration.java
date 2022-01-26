package com.java.main.cache;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.java.main.cache.helpers.GoogleCacheService;
import com.java.main.cache.service.BaseCacheService;

@Configuration
@EnableCaching
public class CacheConfiguration extends CachingConfigurerSupport {
	// Ehcache is the central interface. Caches have Elements and are managed by the CacheManager.
	// The Cache performs logical actions. It delegates physical implementations to its Stores.

	// A reference to an EhCache can be obtained through the CacheManager.
	// An Ehcache thus obtained is guaranteed to have status Status.STATUS_ALIVE.
	// This status is checked for any method which throws IllegalStateException and the same thrown if it is not alive.
	// This would normally happen if a call is made after CacheManager.shutdown() is invoked.

	// Cache Manager: A container for Ehcaches that maintain all aspects of their lifecycle.
	// A CacheManager holds references to Caches and Ehcaches and manages their creation and lifecycle.

	@Bean
	public BaseCacheService baseCacheService() {
		return new GoogleCacheService();
	}

	@Bean
	public EhCacheCacheManager ehCacheCacheManager() {

		return new EhCacheCacheManager(ehCacheManagerFactoryBean().getObject());
	}

	@Bean
	public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
		EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
		cacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
		cacheManagerFactoryBean.setShared(true);
		return cacheManagerFactoryBean;
	}

}
