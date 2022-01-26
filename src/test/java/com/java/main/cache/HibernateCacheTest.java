package com.java.main.cache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.CacheManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.java.main.profile.SpringProfiles;
import com.java.main.repositories.LocationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = { SpringProfiles.H2, SpringProfiles.TEST, SpringProfiles.CACHE })
@Slf4j
public class HibernateCacheTest {

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
