package com.java.main.controller;

import com.java.main.models.Status;
import com.java.main.models.dtos.MachineDTO;
import com.java.main.models.dtos.OperationDTO;
import com.java.main.services.MachineService;
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
class MachineControllerTest {

    @Mock
    private MachineService machineService;

    @InjectMocks
    private MachineController controller;


    Set<MachineDTO> machineDTOSet;


    @BeforeEach
    void setUp() {
        this.machineDTOSet = new HashSet<>();

        MachineDTO machine1 = new MachineDTO(1,"machine1");
        machine1.addOperation(new OperationDTO(1,"operation1", Status.setup));
        machine1.addOperation(new OperationDTO(1,"operation1", Status.archive));

        MachineDTO machine2 = new MachineDTO(2,"machine2");
        machine2.addOperation(new OperationDTO(1,"operation1", Status.in_production));
        machine2.addOperation(new OperationDTO(1,"operation1", Status.over_production));


        this.machineDTOSet.add(machine1);
        this.machineDTOSet.add(machine2);
    }



    @Test
    public void testGetMachinesUrl() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));


        Mockito.when(machineService.getAllMachines())
                .thenReturn(this.machineDTOSet);

        ResponseEntity<Set<MachineDTO>> responseEntity = controller.getAllMachines();

        assertEquals(responseEntity.getStatusCodeValue(),200);
        assertNotNull(responseEntity.getBody());
        assertEquals(2, responseEntity.getBody().size());

    }



    @Test
    public void testGetAllMachinesSpecificProductionOrder() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));


        Mockito.when(machineService.getAllMachinesWithSetupAndInOverEndingProductionStatus())
                .thenReturn(this.machineDTOSet);

        ResponseEntity<Set<MachineDTO>> responseEntity = controller.getAllMachinesSpecificProductionOrder();

        assertEquals(responseEntity.getStatusCodeValue(),200);
        assertNotNull(responseEntity.getBody());
        assertEquals(2, responseEntity.getBody().size());

    }

}