package com.java.main.controller;

import com.java.main.models.dtos.MachineDTO;
import com.java.main.models.dtos.MaterialDTO;
import com.java.main.models.dtos.MeasuredValueDTO;
import com.java.main.services.MaterialService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MaterialControllerTest {

    @Mock
    private MaterialService materialService;

    @InjectMocks
    private MaterialController controller;


    Set<MachineDTO> machineDTOSet;
    Set<MaterialDTO> materialDTOSet;


    @BeforeEach
    void setUp() {
        this.machineDTOSet = new HashSet<>();

        MachineDTO machine1 = new MachineDTO(1,"machine1");
        MachineDTO machine2 = new MachineDTO(2,"machine2");

        this.machineDTOSet.add(machine1);
        this.machineDTOSet.add(machine2);


        this.materialDTOSet = new HashSet<>();

        MaterialDTO material1 = new MaterialDTO(1,"Plastic");
        material1.setMeasuredValue(new MeasuredValueDTO(10,"kilo"));
        materialDTOSet.add(material1);

    }



    @Test
    public void testGetMaterialsUrl()  {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));


        Mockito.when(materialService.getAllMaterials())
                .thenReturn(this.materialDTOSet);

        ResponseEntity<Set<MaterialDTO>> responseEntity = controller.getAllMaterials();

        assertEquals(responseEntity.getStatusCodeValue(),200);
        assertNotNull(responseEntity.getBody());
        assertEquals(1, responseEntity.getBody().size());

    }

}