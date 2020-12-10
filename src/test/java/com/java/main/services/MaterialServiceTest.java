package com.java.main.services;

import com.java.main.mappers.MaterialMapperImpl;
import com.java.main.models.Material;
import com.java.main.models.MeasuredValue;
import com.java.main.models.Operation;
import com.java.main.models.dtos.MaterialDTO;
import com.java.main.repositories.MaterialRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;


import java.util.HashSet;
import java.util.Set;


@ExtendWith(MockitoExtension.class)
class MaterialServiceTest {

    @Mock
    private MaterialRepository materialRepository;

    @Spy
    private MaterialMapperImpl mapper;

    @InjectMocks
    private MaterialService materialService;

    Set<Material> materialSet;


    @BeforeEach
    void setUp() {
        this.materialSet = new HashSet<>();

        Material material1 = new Material(1,"Plastic");
        material1.addOperation(new Operation(1,"operation1"));
        material1.addOperation(new Operation(2,"operation2"));
        material1.setMeasuredValue(new MeasuredValue(10,"kilo"));

        materialSet.add(material1);
    }


    @Test
    @DisplayName("Testing getting machine joining operations list with dummy values")
    public void testGetMachineListFilled() {

        Mockito.when(materialRepository.findAll())
                .thenReturn(this.materialSet);

        when(mapper.mapMaterialSetToMaterialDtoSet(this.materialSet)).thenCallRealMethod();

        Set<MaterialDTO> materialDTOSet = materialService.getAllMaterials();

        assertEquals(1,materialDTOSet.size());
    }

}