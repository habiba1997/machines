package com.java.main.cache;

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

import com.java.main.SpringBootTestApplication;
import com.java.main.cache.service.ClearCacheService;
import com.java.main.profile.SpringProfiles;
import com.java.main.repositories.LocationRepository;

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
	private LocationRepository locationRepository;

	@Test
	public void testEntityCache() {
		// we use Ehcache API directly to verify that com.baeldung.hibernate.cache.model.Foo cache is not empty after we load a Foo instance.
		// this to validate @Cache on each entity
		locationRepository.findByName("mald");
		// the fist cache manager is default
		int locationEntityCacheSize = CacheManager.ALL_CACHE_MANAGERS.get(1).getCache("com.java.main.models.entity.LocationEntity").getSize();
		int operationEntityCacheSize = CacheManager.ALL_CACHE_MANAGERS.get(1).getCache("com.java.main.models.entity.OperationEntity").getSize();
		assertTrue(locationEntityCacheSize > 0);
		assertEquals(operationEntityCacheSize, 0);
	}
}
