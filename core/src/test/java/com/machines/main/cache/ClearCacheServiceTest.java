package com.machines.main.cache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import net.sf.ehcache.CacheManager;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.machines.main.SpringBootTestApplication;
import com.machines.main.cache.service.ClearCacheServiceImpl;
import com.machines.main.profile.SpringProfiles;
import com.machines.main.repositories.LocationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootTestApplication.class)
@ActiveProfiles(profiles = { SpringProfiles.CACHE, SpringProfiles.CACHE_TEST, SpringProfiles.HSQL, SpringProfiles.TEST })
public class ClearCacheServiceTest {

	public static final String LOCATION_NAME = "mald";
	@Autowired
	private ClearCacheServiceImpl cacheService;

	@Autowired
	private CacheServiceTestCustomComponentService customComponentService;

	@Autowired

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
	private LocationRepository locationRepository;

	@Test
	public void testEntityCache() {
		// we use Ehcache API directly to verify that com.baeldung.hibernate.cache.model.Foo cache is not empty after we load a Foo instance.
		// this to validate @Cache on each entity
		locationRepository.findByName(LOCATION_NAME);
		// the fist cache manager is default
		int locationEntityCacheSize = CacheManager.ALL_CACHE_MANAGERS.get(0).getCache("com.machines.main.models.entity.LocationEntity").getSize();
		int operationEntityCacheSize = CacheManager.ALL_CACHE_MANAGERS.get(0).getCache("com.machines.main.models.entity.OperationEntity").getSize();
		assertTrue(locationEntityCacheSize > 0);
		assertEquals(operationEntityCacheSize, 0);
	}
}
