package com.java.main.cache.service;

import java.util.Map;

import javax.persistence.EntityManager;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.java.main.cache.helpers.CacheInvalidation;
import com.java.main.profile.CacheConstants;

// First-level cache: is a session scoped cache which ensures that each entity instance is loaded only once in the persistent context.
// Once the session is closed, first-level cache is terminated as well.
// This is actually desirable, as it allows for concurrent sessions to work with entity instances in isolation from each other.
//
// On the other hand, second-level cache is SessionFactory-scoped, meaning it is shared by all sessions created with the same session factory.
// When an entity instance is looked up by its id (either by application logic or by Hibernate internally,
// e.g. when it loads associations to that entity from other entities), and if second-level caching is enabled for that entity, the following happens:
//    If an instance is already present in the first-level cache, it is returned from there
//    If an instance is not found in the 1st cache, and the corresponding instance state is cached in the 2nd-level cache,
//    	then the data is fetched from there and an instance is assembled and returned
//    Otherwise, the necessary data are loaded from the database and an instance is assembled and returned
// Once the instance is stored in the persistence context (first-level cache),
// it is returned from there in all subsequent calls within the same session until the session is closed or the instance is manually evicted from the persistence context.
// Also, the loaded instance state is stored in L2 cache if it was not there already.
@Slf4j
@Service
public class ClearCacheService {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private CacheManager cacheManager;

	@Autowired
	private ApplicationContext applicationContext;

	public void refreshAllCache() {
		clearAllHibernateCacheRegion();
		clearAllSpringCache();
		invalidateOurCreatedCache(CacheConstants.ALL, null);
	}

	public void clearAllCache() {
		clearAllHibernateCacheRegion();
		clearAllSpringCache();
		deleteOurCreatedCache(CacheConstants.ALL, null);
	}

	/**
	 * Will clear all JPA/Hibernates caches.
	 */
	private void clearAllHibernateCacheRegion() {
		Session session = (Session) entityManager.getDelegate();
		SessionFactory sessionFactory = session.getSessionFactory();
		// get cache
		org.hibernate.Cache cache = sessionFactory.getCache();
		if (cache != null) {
			cache.evictAllRegions();
		}
	}

	public void clearAllSpringCache() {
		for (String cacheName : cacheManager.getCacheNames()) {
			clearSpringCache(cacheName, null);
		}
	}

	private void clearSpringCache(final String cacheName, final String key) {
		if (cacheManager.getCacheNames().contains(cacheName)) {
			// Check if cache exist to avoid on-fly creation
			Cache cache = cacheManager.getCache(cacheName);
			if (cache != null) {
				if (StringUtils.isBlank(key)) {
					cache.clear();
				} else {
					cache.evict(key);
				}
			}
		}
	}

	/**
	 * This method will call all interface that implement MesCacheInvalidation to invalidate and/or reload caches.
	 *
	 * @param entityName
	 * @param key
	 */
	public void invalidateOurCreatedCache(final String entityName, final String key) {
		Map<String, CacheInvalidation> services = applicationContext.getBeansOfType(CacheInvalidation.class);
		for (Map.Entry<String, CacheInvalidation> entry : services.entrySet()) {
			String beanName = entry.getKey();
			CacheInvalidation bean = entry.getValue();
			if (bean.doesCacheExist(entityName)) {
				log.debug("call invalidateCache on {} {} {} ", beanName, entityName, key);
				bean.invalidateCache(entityName, key);
			}
		}
	}

	/**
	 * This method will call all interface that implement MesCacheInvalidation to delete all caches
	 *
	 * @param entityName
	 * @param key
	 */
	public void deleteOurCreatedCache(final String entityName, final String key) {
		Map<String, CacheInvalidation> services = applicationContext.getBeansOfType(CacheInvalidation.class);
		for (Map.Entry<String, CacheInvalidation> entry : services.entrySet()) {
			String beanName = entry.getKey();
			CacheInvalidation bean = entry.getValue();
			if (bean.doesCacheExist(entityName)) {
				log.debug("call deleteAllCache on {} {} {} ", beanName, entityName, key);
				bean.deleteAllCache(entityName);
			}
		}
	}
}
