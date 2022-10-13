package com.machines.main.cache.service;

import java.time.Duration;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.machines.main.BaseCacheService;
import com.machines.main.cache.CacheableElement;

@Slf4j
public class RedisCacheService<K, V extends CacheableElement<K>> implements BaseCacheService<K, V> {

	public static final String HYPHEN = "_";
	public static final int LOCK_TIME_IN_SECONDS = 20;
	private static final Duration DEFAULT_TIME_TO_LIVE = Duration.ofDays(1);

	private RedisTemplate<String, V> redisTemplate;

	@Autowired
	private RedissonClient redisson;

	@Autowired
	public void setRedisTemplate(final RedisTemplate template) {
		this.redisTemplate = template;
	}

	@Override
	public boolean hasKey(final String cacheName) {
		return redisTemplate.hasKey(cacheName);
	}

	@Override
	public V getCachedElementByKey(final String cacheName, final K key) {
		// opsForHash()
		// Returns the operations performed on hash values.
		return redisTemplate.<String, V>opsForHash().get(cacheName, key);
	}

	@Override
	public Map<K, V> getAllCachedElements(final String cacheName) {
		return redisTemplate.<K, V>boundHashOps(cacheName).entries();
	}

	@Override
	public Long getExpireTime(final String cacheName) {
		return redisTemplate.getExpire(cacheName);
	}

	@Override
	public void putNewCacheElements(final String cacheName, final Collection<V> entries) {
		redisTemplate.opsForHash().putAll(cacheName, entries.stream().collect(Collectors.toMap(V::getCacheKeyElement, Function.identity())));
	}

	@Override
	public void deleteAllElements(final String cacheName) {
		redisTemplate.delete(cacheName);
	}

	@Override
	public boolean acquireLock(final String cacheName, final K key) {
		// Distributed implementation of Lock Implements reentrant lock. Use getHoldCount() to get a holds count.
		try {
			RLock lock = redisson.getLock(lockName(cacheName, key));
			lock.lock(LOCK_TIME_IN_SECONDS, TimeUnit.SECONDS);
			return true;
		} catch (IllegalStateException e) {
			log.error("*** redis cache - an error occurred while acquiring a lock with name {}", lockName(cacheName, key), e);
			return false;
		}
	}

	private String lockName(final String cacheName, final K key) {
		String lockName = cacheName + HYPHEN + "LOCK";
		if (key != null) {
			return lockName + HYPHEN + key;
		}
		return lockName;
	}

	@Override
	public void releaseLock(final String cacheName, final K key) {
		boolean releaseLock = redisson.getLock(lockName(cacheName, key)).forceUnlock();
		if (!releaseLock) {
			log.error("*** redis cache - an error occurred while releasing a lock with name {}", cacheName);
		}
	}

	@Override
	public boolean isLocked(final String cacheName, final K key) {
		// Redisson's distributed locks allow for thread synchronization across applications/servers.
		// Redisson's Lock implements java.util.concurrent.locks.Lock interface.
		return redisson.getLock(lockName(cacheName, key)).isLocked();
	}

	@Override
	public void setTimeToLive(final String cacheName, final Duration customTimeToLive) {
		Duration timeToLive = customTimeToLive != null ? customTimeToLive : DEFAULT_TIME_TO_LIVE;
		redisTemplate.expire(cacheName, timeToLive);
	}
}
