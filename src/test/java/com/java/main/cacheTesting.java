package com.java.main;


import com.java.main.models.Machine;
import com.java.main.models.Material;
import com.java.main.repositories.MachineRepository;
import com.java.main.repositories.MaterialRepository;
import org.hibernate.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@SpringBootTest
public class cacheTesting {

    @Autowired
    EntityManager entityManager;


    @Autowired
    MachineRepository machine_repository;

    @Autowired
    MaterialRepository material_repository;

    @Test
    @Transactional
    public void testMachineCaching()
    {
        Session session = entityManager.unwrap(Session.class);
        Machine machine = machine_repository.findById(1).get();
        machine_repository.findById(1);

        session.evict(machine);
        machine_repository.findById(1);
    }

    @Test
    @Transactional
    public void testMaterialCaching()
    {
        Session session = entityManager.unwrap(Session.class);
        Material material = material_repository.findById(1).get();
        material_repository.findById(1);

        session.evict(material);
        material_repository.findById(1);
    }
}
