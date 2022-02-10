package com.java.main.cache.service;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.java.main.cache.helpers.CacheInvalidation;
import com.java.main.cache.helpers.CacheableElement;
import com.java.main.profile.CacheConstants;

@Slf4j
public abstract class CacheService<K, V extends CacheableElement<K>> implements CacheInvalidation<K> {

	// myService => baseCacheService => autowire object of CacheService depending on profile => cache service contain all my required methods

	@Autowired
	private BaseCacheService<K, V> cacheService;

	protected abstract String getCacheName();

	protected abstract Duration getTimeToLive();

	protected abstract List<V> findAllItemsFromDatabase();

	public boolean isCachePopulated() {
		String cacheName = getCacheName();
		return cacheService.hasKey(cacheName) && cacheService.getAllCachedElements(cacheName) != null
				&& cacheService.getExpireTime(cacheName) > BaseCacheService.CACHE_EXPIRATION_LIMIT;
	}

	// just to make ethe database call transactional
	@Transactional
	public Map<K, V> fetchAndLoadAllCachedEntries() {
		try {
			if (isCachePopulated()) {
				log.debug("*** cache - serving cached data for model {}.", getCacheName());
				return cacheService.getAllCachedElements(getCacheName());
			}
			if (cacheService.isLocked(getCacheName(), null)) {
				while (cacheService.isLocked(getCacheName(), null)) {
					Thread.sleep(BaseCacheService.LOCK_WAIT_INTERVAL_MILLIS);
				}
				if (isCachePopulated()) {
					return cacheService.getAllCachedElements(getCacheName());
				}
			}
		} catch (Throwable ex) {
			log.error("*** cache - error occurred fetch cached entries for model {}", getCacheName(), ex);
		}
		// no cache saved nor is cache is locked => get from database directly
		forceUpdateCacheFromDb();
		Map<K, V> cachedEntries = cacheService.getAllCachedElements(getCacheName());
		return cachedEntries == null ? Collections.emptyMap() : cachedEntries;
	}

	@Transactional
	public void forceUpdateCacheFromDb() {
		if (cacheService.acquireLock(getCacheName(), null)) {
			try {
				long startTime = System.currentTimeMillis();
				log.debug("*** cache - loading data from DB for model {}", getCacheName());
				List<V> itemFromDb = findAllItemsFromDatabase();
				long endTime = System.currentTimeMillis();
				log.debug("*** cache - data loaded successfully from DB for model {} in {} milliseconds.", getCacheName(), endTime - startTime);
				populateCacheEntries(itemFromDb);
			} catch (Exception ex) {
				log.error("error occurred trying to update cache from DB for model {}.", getCacheName(), ex);
			} finally {
				cacheService.releaseLock(getCacheName(), null);
			}
		}

	}

	public V getValueByKey(final K key) {
		try {
			if (cacheService.isLocked(getCacheName(), null)) {
				while (cacheService.isLocked(getCacheName(), null)) {
					Thread.sleep(BaseCacheService.LOCK_WAIT_INTERVAL_MILLIS);
				}
			}
			if (isCachePopulated()) {
				log.debug("*** cache - serving cached data for model {}.", getCacheName());
				return cacheService.getCachedElementByKey(getCacheName(), key);
			}
		} catch (Throwable ex) {
			log.error("*** cache - error occurred fetch cached entries for model {}", getCacheName(), ex);
		}
		// no cache saved nor is cache is locked => get from database directly
		forceUpdateCacheFromDb();
		return cacheService.getCachedElementByKey(getCacheName(), key);
	}

	@Override
	public boolean doesCacheExist(final String entityName) {
		return CacheConstants.ALL.equals(entityName) || getCacheName().equals(entityName);
	}

	@Override
	public void invalidateCache(final String entityName, final K key) {
		deleteAllCachedEntries();
		fetchAndLoadAllCachedEntries();
	}

	@Override
	public void deleteAllCache(final String entityName) {
		deleteAllCachedEntries();
	}

	private void populateCacheEntries(final List<V> entries) {
		try {
			cacheService.putNewCacheElements(getCacheName(), entries);
			cacheService.setTimeToLive(getCacheName(), getTimeToLive());
		} catch (Throwable ex) {
			log.error("*** cache - error occurred trying to write cache entries for model {}.", getCacheName(), ex);
		}
	}

	private void deleteAllCachedEntries() {
		try {
			cacheService.deleteAllElements(getCacheName());
		} catch (Throwable ex) {
			log.error("*** cache - error occurred trying to delete all cached entries for model {}", getCacheName(), ex);
		}
	}

}
