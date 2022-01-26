package com.java.main.services;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

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
@Service
public class CacheService {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private CacheManager cacheManager;

	@CacheEvict(value = CacheConstants.MACHINES, allEntries = true)
	public void clearMachinesCache() {
	}

	/**
	 * Will clear all JPA/Hibernates caches.
	 */
	private void clearAllHibernateCacheRegion() {
		Session session = (Session) entityManager.getDelegate();
		SessionFactory sessionFactory = session.getSessionFactory();
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

}
