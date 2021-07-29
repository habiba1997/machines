package com.java.main.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.java.main.mappers.MaterialMapper;
import com.java.main.models.Material;
import com.java.main.models.MeasuredValue;
import com.java.main.models.Operation;
import com.java.main.models.dtos.MaterialDTO;
import com.java.main.models.dtos.MeasuredValueDTO;
import com.java.main.repositories.MaterialRepository;


@ExtendWith(MockitoExtension.class)
class MaterialServiceImplTest {

    @Mock
    private MaterialRepository materialRepository;

    @Mock
    private MaterialMapper mapper;

    @InjectMocks
	private MaterialServiceImpl materialServiceImpl;

    Set<Material> materialSet;
    Set<MaterialDTO> materialDTOSet;

    Material material1;

    @BeforeEach
    void setUp() {
        this.materialSet = new HashSet<>();
        materialDTOSet = new HashSet<>();

        material1 = new Material(1,"Plastic");
        material1.addOperation(new Operation(1,"operation1"));
        material1.addOperation(new Operation(2,"operation2"));
        material1.setMeasuredValue(new MeasuredValue(10,"kilo"));

        materialSet.add(material1);

        materialDTOSet.add(new MaterialDTO(1,"Plastic", new MeasuredValueDTO(10,"kilo")));

    }


    @Test
    @DisplayName("Testing getting machine joining operations list with dummy values")
    public void testGetMachineListFilled() {
        Mockito.when(materialRepository.findAll())
                .thenReturn(this.materialSet);

        when(mapper.mapMaterialSetToMaterialDtoSet(this.materialSet)).thenReturn(materialDTOSet);

		Set<MaterialDTO> materialDTOSet = materialServiceImpl.getAllMaterials();

        assertEquals(1,materialDTOSet.size());
    }

}