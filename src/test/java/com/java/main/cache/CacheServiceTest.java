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
import com.java.main.repositories.LocationRepository;
import com.java.main.repositories.MachineRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootTestApplication.class)
@ActiveProfiles(profiles = { SpringProfiles.CACHE_TEST, SpringProfiles.H2, SpringProfiles.TEST, SpringProfiles.CACHE, SpringProfiles.SQL_LOG })
public class CacheServiceTest {

	@Autowired
	private ClearCacheService cacheService;

	@Autowired
	private CacheServiceTestCustomComponentService customComponentService;

	@Test
	public void testCache() {
		Assert.assertEquals("should increment first time", 1, customComponentService.incrementCache());
		Assert.assertEquals("should not increment due to cache", 1, customComponentService.incrementCache());

		cacheService.clearAllCache();
		Assert.assertEquals("should not increment because cache has been refresh", 2, customComponentService.incrementCache());
		Assert.assertEquals("should not increment due to cache", 2, customComponentService.incrementCache());

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
		long timeAfterCacheClear = time5 - time4;

		assertTrue(timeDatabaseFetch > timeCacheFetch);
		assertTrue(timeCacheFetch <= timeAfterCacheClear);
	}

	@Autowired
	private LocationRepository locationRepository;

	@Test
	public void testLocationCache() {
		long startTime = System.currentTimeMillis();
		locationRepository.findByName("mald");
		long afterDatabaseCall = System.currentTimeMillis();
		locationRepository.findByName("mald");
		long afterCacheCall = System.currentTimeMillis();
		assertTrue(afterDatabaseCall - startTime > (afterCacheCall - afterDatabaseCall));

	}
}
