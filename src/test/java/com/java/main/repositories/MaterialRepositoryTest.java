package com.java.main.repositories;

import com.java.main.models.Material;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
class MaterialRepositoryTest {

    @Autowired
    private MaterialRepository materialRepository;

    @Test
    public void testFindAllReturnNotNull()
    {
        Set<Material> set = materialRepository.findAll();
        assertNotNull(set);
    }

    @Test
    public void testFindAllReturnNotEmpty()
    {
        Set<Material> set = materialRepository.findAll();
        assertFalse(set.isEmpty());
    }

}