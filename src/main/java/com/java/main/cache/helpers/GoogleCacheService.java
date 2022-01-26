package com.java.main.cache.helpers;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.java.main.cache.service.BaseCacheService;

@Slf4j
public class GoogleCacheService<K, V extends CacheableElement<K>> implements BaseCacheService<K, V> {

	// Implementations of cache interface are expected to be thread-safe, and can be safely accessed by multiple concurrent threads.
	private Cache<String, Map<K, V>> cacheMap = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).build();

	// The ReentrantLock class implements the Lock interface and provides synchronization to methods while accessing shared resources.
	// The code which manipulates the shared resource is surrounded by calls to lock and unlock method.
	// This gives a lock to the current working thread and blocks all other threads which are trying to take a lock on the shared resource.
	private ReentrantLock wholeEntityLock = new ReentrantLock();

	@Override
	public boolean hasKey(String cacheName) {
		return cacheMap.asMap().containsKey(cacheName);
	}

	@Override
	public V getCachedElementByKey(final String cacheName, final K key) {
		return cacheMap.asMap().get(cacheName).get(key);
	}

	@Override
	public Map<K, V> getAllCachedElements(final String cacheName) {
		return cacheMap.asMap().get(cacheName);
	}

	@Override
	public void putNewCacheElements(final String cacheName, final Collection<V> entries) {
		Map<K, V> newEntries = entries.stream().collect(Collectors.toMap(V::getCacheKeyElement, Function.identity()));
		if (cacheMap.asMap().containsKey(cacheName)) {
			newEntries.putAll(cacheMap.asMap().get(cacheName));
		}
		cacheMap.put(cacheName, newEntries);
	}

	@Override
	public void deleteAllElements(final String cacheName) {
		cacheMap.invalidate(cacheName);
	}

	@Override
	public boolean acquireLock(final String cacheName, final K key) {
		try {
			while (wholeEntityLock.isLocked()) {
				Thread.sleep(LOCK_WAIT_INTERVAL_MILLIS);
			}
			wholeEntityLock.lock();
			return true;
		} catch (Exception ex) {
			log.error("Exception occurred while locking cache {} to write", cacheName);
		}
		return false;
	}

	@Override
	public void releaseLock(final String cacheName, final K key) {
		wholeEntityLock.unlock();
	}

	@Override
	public boolean isLocked(final String cacheName, final K key) {
		return wholeEntityLock.isLocked();
	}
}
