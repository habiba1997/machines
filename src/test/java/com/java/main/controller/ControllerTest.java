package com.java.main.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.java.main.models.dtos.MachineDTO;
import com.java.main.models.dtos.MaterialDTO;
import com.java.main.models.dtos.MeasuredValueDTO;
import com.java.main.services.MachineService;
import com.java.main.services.MaterialService;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;import java.util.HashSet;

import java.util.Set;


//@WebMvcTest annotation is used for Spring MVC tests.
// It disables full auto-configuration and instead apply only configuration relevant to MVC tests.
@WebMvcTest(Controller.class)
class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @MockBean
    private MachineService machineService;

    @MockBean
    private MaterialService materialService;


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
    public void testGetMachinesUrlUsingServiceDirectly() throws Exception {

        Mockito.when(machineService.getAllMachines())
                .thenReturn(this.machineDTOSet);

        String url = "/machines";
        MvcResult mvcResult = mockMvc.perform(get(url))
                .andExpect(status().isOk()).andReturn();

        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        String expectedJsonResponse = objectMapper.writeValueAsString(machineDTOSet);
        assertEquals(expectedJsonResponse, actualJsonResponse);

    }



    @Test
    public void testGetMaterialsUrlUsingServiceDirectly() throws Exception {

        Mockito.when(materialService.getAllMaterials())
                .thenReturn(this.materialDTOSet);

        String url = "/materials";
        MvcResult mvcResult = mockMvc.perform(get(url))
                .andExpect(status().isOk()).andReturn();

        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        String expectedJsonResponse = objectMapper.writeValueAsString(materialDTOSet);
        assertEquals(expectedJsonResponse, actualJsonResponse);

    }

}