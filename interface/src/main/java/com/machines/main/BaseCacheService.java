package com.machines.main;

import java.time.Duration;
import java.util.Collection;
import java.util.Map;

import com.machines.main.cache.CacheableElement;

public interface BaseCacheService<K, V extends CacheableElement<K>> {

	int LOCK_WAIT_INTERVAL_MILLIS = 3000;
	long CACHE_EXPIRATION_LIMIT = 300L;

	boolean hasKey(String cacheName);

	V getCachedElementByKey(String cacheName, K key);

	Map<K, V> getAllCachedElements(String cacheName);

	void putNewCacheElements(String cacheName, Collection<V> entries);

	void deleteAllElements(String cacheName);

	boolean acquireLock(String cacheName, K key);

	void releaseLock(String cacheName, K key);

	boolean isLocked(String cacheName, K key);

	void setTimeToLive(String cacheName, Duration customTimeToLive);

	Long getExpireTime(String cacheName);

}
