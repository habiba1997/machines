package com.java.main.repositories;

import com.java.main.models.Material;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(MockitoExtension.class)
class MaterialRepositoryTest {

    @Mock
    private MaterialRepository materialRepository;

    @Test
    public void testFindAllReturnNotNull()
    {
        Set<Material> set = materialRepository.findAll();
        assertNotNull(set);
    }


}