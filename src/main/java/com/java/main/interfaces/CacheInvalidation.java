package com.java.main.interfaces;

public interface CacheInvalidation<K> {

	boolean doesCacheExist(String entityName);

	void invalidateCache(String entityName, K key);

	void deleteAllCache(String entityName);
}
