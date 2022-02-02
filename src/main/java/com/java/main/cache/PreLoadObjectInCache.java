package com.java.main.cache;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.java.main.impl.LocationServiceImpl;
import com.java.main.profile.SpringProfiles;

/**
 * Preload objects in cache on startup.
 */
@Component
@Slf4j
@Profile({ SpringProfiles.NO_TEST })
@ConditionalOnProperty(name = "load.cache.on.startup", havingValue = "true", matchIfMissing = true)
public class PreLoadObjectInCache {

	@Autowired
	private LocationServiceImpl locationService;

	@PostConstruct
	public void init() {
		loadLocationCache();
	}

	@Transactional
	public void loadLocationCache() {
		if (!locationService.isCachePopulated()) {
			locationService.forceUpdateCacheFromDb();
		}
	}
}
