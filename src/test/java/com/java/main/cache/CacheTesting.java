package com.java.main.cache;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.java.main.models.Machine;
import com.java.main.models.Material;
import com.java.main.profile.SpringProfiles;
import com.java.main.repositories.MachineRepository;
import com.java.main.repositories.MaterialRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Profile({ SpringProfiles.HSQL })
public class CacheTesting {

	@Autowired
	EntityManager entityManager;

	@Autowired
	MachineRepository machineRepository;

	@Autowired
	MaterialRepository materialRepository;

	@Test
	@Transactional
	public void testMachineCaching() {
		Session session = entityManager.unwrap(Session.class);
		Optional<Machine> optionalMachine = machineRepository.findById(1);
		if (optionalMachine.isPresent()) {
			Machine machine = optionalMachine.get();
			machineRepository.findById(1);

			session.evict(machine);
			machineRepository.findById(1);
		}
	}

	@Test
	@Transactional
	public void testMaterialCaching() {
		Session session = entityManager.unwrap(Session.class);
		Optional<Material> optionalMaterial = materialRepository.findById(1);
		if (optionalMaterial.isPresent()) {
			Material material = optionalMaterial.get();
			materialRepository.findById(1);

			session.evict(material);
			materialRepository.findById(1);
		}

	}
}
