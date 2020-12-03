package com.java.main;

import com.java.main.models.Material;
import com.java.main.repositories.Material_Repository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class testUrls {

    @Autowired
    Material_Repository material_repository;

    @Test
    public void testGetMaterial()
    {
        Iterable<Material> materials = material_repository.findAll();
        materials.forEach(material -> System.out.println(material.getName()));
    }


}
