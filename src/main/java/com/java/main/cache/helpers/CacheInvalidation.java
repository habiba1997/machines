package com.java.main.cache.helpers;

public interface CacheInvalidation<K> {

	boolean doesCacheExist(String entityName);

	void invalidateCache(String entityName, K key);
}
