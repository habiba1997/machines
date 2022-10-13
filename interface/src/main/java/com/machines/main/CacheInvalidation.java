package com.machines.main;

public interface CacheInvalidation<K> {

	boolean doesCacheExist(String entityName);

	void invalidateCache(String entityName, K key);

	void deleteAllCache(String entityName);
}
