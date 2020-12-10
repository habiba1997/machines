package com.java.main.controller;

import com.java.main.models.dtos.OperationDTO;
import com.java.main.models.dtos.operation.OperationDTOWithMaterialMachine;
import com.java.main.services.OperationService;
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
class OperationControllerTest {

    @Mock
    private OperationService operationService;

    @InjectMocks
    private OperationController controller;


    Set<OperationDTO> operationDTOSet;
    OperationDTOWithMaterialMachine operationDTOWithMaterialMachine;


    @BeforeEach
    void setUp() {
        operationDTOWithMaterialMachine = new OperationDTOWithMaterialMachine();
        operationDTOSet = new HashSet<>();
    }

    @Test
    public void testGettAllOperationsWithSetupAndInOverEndingProductionStatus() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Mockito.when(operationService.getAllOperationsWithSetupAndInOverEndingProductionStatus())
                .thenReturn(this.operationDTOSet);

        ResponseEntity<Set<OperationDTO>> responseEntity = controller.getAllOperationsWithSetupAndInOverEndingProductionStatus();
        assertEquals(responseEntity.getStatusCodeValue(),200);
    }


    @Test
    public void testGetOperationById() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Mockito.when(operationService.getOperationById(1))
                .thenReturn(this.operationDTOWithMaterialMachine);

        ResponseEntity<OperationDTOWithMaterialMachine> responseEntity = controller.getOperationById(1);
        assertEquals(responseEntity.getStatusCodeValue(),200);
    }



    @Test
    public void testTogglePercentageColor() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Mockito.when(operationService.togglePercentageColor(1))
                .thenReturn(this.operationDTOWithMaterialMachine);

        ResponseEntity<OperationDTOWithMaterialMachine> responseEntity = controller.togglePercentageColor(1);
        assertEquals(responseEntity.getStatusCodeValue(),200);
    }

    @Test
    public void testGetOperationIfSpecificProductionOrder() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Mockito.when(operationService.getOperationIfWithSetupInOverEndingProductionOrder(1))
                .thenReturn(this.operationDTOWithMaterialMachine);

        ResponseEntity<OperationDTOWithMaterialMachine> responseEntity = controller.operationIfSpecificProductionOrder(1);
        assertEquals(responseEntity.getStatusCodeValue(),200);
    }



}