package com.java.main.cache;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

import javax.persistence.EntityManager;

import net.sf.ehcache.CacheManager;

import org.hibernate.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.java.main.models.entity.LocationEntity;
import com.java.main.profile.SpringProfiles;
import com.java.main.repositories.LocationRepository;

//todo (need jpa profile be on
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = { SpringProfiles.H2, SpringProfiles.TEST, SpringProfiles.CACHE })
public class HibernateCacheTest {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private LocationRepository locationRepository;

	@Test
	public void testLocationCache() {
		// three calls to database => unsuccessful case
		Session session = entityManager.unwrap(Session.class);
		System.out.println(" 1st Database Call For Location");
		LocationEntity locationEntity = locationRepository.findByName("mald");
		if (locationEntity != null) {
			System.out.println(" 2nd Database Call For Location");
			locationRepository.findByName("mald");

			session.evict(locationEntity);
			System.out.println(" 3rd Database Call For Location");
			locationRepository.findByName("mald");
		}
	}


	@Test
	public void testEntityCache() {
		// we use Ehcache API directly to verify that com.baeldung.hibernate.cache.model.Foo cache is not empty after we load a Foo instance.
		// this to validate @Cache on each entity
		locationRepository.findByName("mald");
		// the fist cache manager is default
		int locationEntityCacheSize = CacheManager.ALL_CACHE_MANAGERS.get(1).getCache("com.java.main.models.entity.LocationEntity").getSize();
		int operationEntityCacheSize = CacheManager.ALL_CACHE_MANAGERS.get(1).getCache("com.java.main.models.entity.OperationEntity").getSize();
		assertThat(locationEntityCacheSize, greaterThan(0));
		assertThat(operationEntityCacheSize, equalTo(0));
	}
}
