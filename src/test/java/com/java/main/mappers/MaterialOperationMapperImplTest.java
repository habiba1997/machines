package com.java.main.mappers;

import com.java.main.models.Material;
import com.java.main.models.MeasuredValue;
import com.java.main.models.Operation;
import com.java.main.models.dtos.MaterialDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MaterialOperationMapperImplTest {

    @InjectMocks
    private MaterialOperationMapperImpl impl;

    Set<Material> materialSet;

    @BeforeEach
    void setup()
    {
        materialSet = new HashSet<>();

        Material material1 = new Material(1,"Plastic");
        material1.addOperation(new Operation(1,"operation1"));
        material1.addOperation(new Operation(2,"operation2"));
        material1.setMeasuredValue(new MeasuredValue(10,"kilo"));

        materialSet.add(material1);

    }

    @Test
    void testMapMaterialToDTO() {
        Material material = materialSet.iterator().next();

        MaterialDTO materialDTO = impl.materialToMaterialDTO(material);

        assertEquals(material.getName(), materialDTO.getName());
        assertEquals(material.getId(), materialDTO.getId());
        assertEquals(2,materialDTO.getOperations().size());
    }

    @Test
    void testMapMaterialSetToMaterialDtoSet() {
        Set<MaterialDTO> materialDTOSet = impl.mapMaterialSetToMaterialDtoSet(materialSet);
        assertEquals(materialDTOSet.size(), materialSet.size());
    }
}