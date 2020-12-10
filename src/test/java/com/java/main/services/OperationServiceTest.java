package com.java.main.services;

import com.java.main.error.ConflictException;
import com.java.main.error.NotFoundException;
import com.java.main.mappers.OperationMapperImpl;
import com.java.main.models.Material;
import com.java.main.models.MeasuredValue;
import com.java.main.models.Operation;
import com.java.main.models.Status;
import com.java.main.models.dtos.OperationDTO;
import com.java.main.models.dtos.operation.OperationDTOWithMaterialMachine;
import com.java.main.repositories.MaterialRepository;
import com.java.main.repositories.OperationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class OperationServiceTest {

    @Mock
    private OperationRepository operationRepository;

    @Spy
    private OperationMapperImpl mapper;

    @InjectMocks
    private OperationService operationService;

    @Mock
    private MaterialRepository materialRepository;


    Status[] statuses;
    Operation operation;
    Operation operationWithSetupStatus;

    Set<Operation> operationSet;
    @BeforeEach
    public void setup(){
        statuses = new Status[]{Status.setup, Status.in_production, Status.over_production, Status.ending_production};
        operation = new Operation(1,"store", Status.planned);

        operationWithSetupStatus = new Operation(1,"label", Status.setup);
        operationWithSetupStatus.setMaterial(new Material(1,"plastic",
                new MeasuredValue(10,"kilo"),false));

        operationSet = new HashSet<>();
        operationSet.add(new Operation(1,"store", Status.ending_production));

    }

    @Test
    public void testFindOperationByStatusIn() {
        Mockito.when(operationRepository.findByStatusIn(statuses))
                .thenReturn(this.operationSet);

        when(mapper.mapOperationsTOSetOfOperationDTOWithMaterialMachine(this.operationSet)).thenCallRealMethod();

        Set<OperationDTO> operationSet = operationService.getAllOperationsWithSetupAndInOverEndingProductionStatus();
        assertEquals(1,operationSet.size());
        assertFalse(operationSet.isEmpty());
    }

    @Test
    public void testGetOperationById() {
        Mockito.when(operationRepository.findOperationById(1))
                .thenReturn(this.operation);

        when(mapper.operationToOperationDTOWithMaterialMachine(this.operation)).thenCallRealMethod();

        OperationDTOWithMaterialMachine actualResponse = operationService.getOperationById(1);

        assertEquals(actualResponse.getName(),"store");
    }

    @Test
    public void testNotFoundExceptionThrown() {
        Mockito.when(operationRepository.findOperationById(1))
                .thenReturn(null);

        assertThatThrownBy( ()-> operationService.getOperationById(1))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Operation required doesn't exist");

    }

    @Test
    public void testGetOperationIfWithSetupInOverEndingProductionOrder() {
        Mockito.when(operationRepository.findOperationById(1))
                .thenReturn(operationWithSetupStatus);

        OperationDTOWithMaterialMachine actualResponse = operationService.getOperationIfWithSetupInOverEndingProductionOrder(1);

        assertEquals(actualResponse.getName(),"label");
    }


    @Test
    public void testGetOperationThrowsConflictException() {
        Mockito.when(operationRepository.findOperationById(1))
                .thenReturn(operation);

        assertThatThrownBy( ()-> operationService.getOperationIfWithSetupInOverEndingProductionOrder(1))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining("No production order step is running on this machine");

    }

    @Test
    public void testTogglePercentageColor() {
        Mockito.when(operationRepository.findOperationById(1))
                .thenReturn(operationWithSetupStatus);

        Mockito.when(materialRepository.save(operationWithSetupStatus.getMaterial()))
                .thenReturn(operationWithSetupStatus.getMaterial());

        OperationDTOWithMaterialMachine actualResponse = operationService.togglePercentageColor(1);

        assertTrue(actualResponse.getMaterial().isPercentageColor());
    }


    @Test
    public void testTogglePercentageColorWithConflict() {
        Mockito.when(operationRepository.findOperationById(1))
                .thenReturn(operation);

        assertThatThrownBy( ()-> operationService.togglePercentageColor(1))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining("No production order step is running on this machine");
    }





}