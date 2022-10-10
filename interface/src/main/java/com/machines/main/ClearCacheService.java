package com.machines.main;

public interface ClearCacheService {

	void refreshAllCache();

	void clearAllCache();

	void clearAllSpringCache();

	void invalidateOurCreatedCache(String entityName, String key);

	void deleteOurCreatedCache(String entityName, String key);
}
