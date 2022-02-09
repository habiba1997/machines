package com.java.main.cache;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.java.main.SpringBootTestApplication;
import com.java.main.cache.service.ClearCacheService;
import com.java.main.profile.SpringProfiles;
import com.java.main.repositories.MachineRepository;
import com.java.main.services.LocationService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootTestApplication.class)
@ActiveProfiles(profiles = { SpringProfiles.CACHE, SpringProfiles.CACHE_TEST, SpringProfiles.HSQL, SpringProfiles.TEST })
public class CacheServiceTest {

	public static final String LOCATION_NAME = "mald";
	@Autowired
	private ClearCacheService cacheService;

	@Autowired
	private CacheServiceTestCustomComponentService customComponentService;

	@Test
	public void testCache() {
		Assert.assertEquals("should increment first time", 1, customComponentService.incrementCache());
		Assert.assertEquals("should not increment due to cache", 1, customComponentService.incrementCache());

		cacheService.refreshAllCache();
		Assert.assertEquals("should not increment because cache has been refresh", 2, customComponentService.incrementCache());
		Assert.assertEquals("should not increment due to cache", 2, customComponentService.incrementCache());

		cacheService.clearAllCache();
		Assert.assertEquals("should increment because cache has been cleared", 3, customComponentService.incrementCache());
		Assert.assertEquals("should not increment due to cache", 3, customComponentService.incrementCache());
	}

	@Autowired
	private MachineRepository machineRepository;

	@Test
	public void testMachineClear() {
		long time0 = System.currentTimeMillis();
		machineRepository.findAll();
		long time1 = System.currentTimeMillis();

		long time2 = System.currentTimeMillis();
		machineRepository.findAll();
		long time3 = System.currentTimeMillis();

		cacheService.clearAllCache();
		long time4 = System.currentTimeMillis();
		machineRepository.findAll();
		long time5 = System.currentTimeMillis();

		long timeDatabaseFetch = time1 - time0;
		long timeCacheFetch = time3 - time2;
		long timeAfterCacheCleared = time5 - time4;

		assertTrue(timeDatabaseFetch > timeCacheFetch);
		assertTrue(timeAfterCacheCleared > timeCacheFetch);
	}

	@Autowired
	private LocationService locationService;

	@Test
	public void testLocationCache() {
		long startTime = System.currentTimeMillis();
		locationService.findByName(LOCATION_NAME);
		long afterDatabaseCall = System.currentTimeMillis();
		locationService.findByName(LOCATION_NAME);
		long afterCacheCall = System.currentTimeMillis();
		cacheService.clearAllCache();
		locationService.findByName(LOCATION_NAME);
		long afterCacheCleared = System.currentTimeMillis();

		long databaseFetchTime = afterDatabaseCall - startTime;
		long cacheFetchTime = afterCacheCall - afterDatabaseCall;
		long databaseFetchAgainAfterCacheCleared = afterCacheCleared - afterCacheCall;
		assertTrue(cacheFetchTime < databaseFetchTime);
		assertTrue(cacheFetchTime < databaseFetchAgainAfterCacheCleared);
	}
}
