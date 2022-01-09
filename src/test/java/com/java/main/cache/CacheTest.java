package com.java.main.cache;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.java.main.dtos.Material;
import com.java.main.models.entity.LocationEntity;
import com.java.main.profile.SpringProfiles;
import com.java.main.repositories.LocationRepository;
import com.java.main.services.MaterialService;

//todo (need jpa profile be on
@RunWith(SpringRunner.class)
@SpringBootTest
@Profile({ SpringProfiles.HSQL })
public class CacheTest {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private MaterialService materialService;

	@Autowired
	private LocationRepository locationRepository;

	@Test
	@Transactional
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
	@Transactional
	public void testMaterialCaching() {
		Session session = entityManager.unwrap(Session.class);
		System.out.println(" 1st Database Call");
		Material material = materialService.findByName("Plastic");
		if (material != null) {
			System.out.println(" NO Database Call => CacheInterceptor");
			materialService.findByName("Plastic");

			System.out.println(" NO Database Call => CacheInterceptor");
			// todo find the session method that invalidate all entities
			// session.clear();
			materialService.findByName("Plastic");
		}
	}
}
