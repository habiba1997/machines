package com.java.main.services;

import com.java.main.mappers.OperationMapperImpl;
import com.java.main.models.Operation;
import com.java.main.models.Status;
import com.java.main.models.dtos.OperationDTO;
import com.java.main.models.dtos.operation.OperationDTOWithMaterialMachine;
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


    Status[] statuses;
    Operation operation;

    Set<Operation> operationSet;
    @BeforeEach
    public void setup(){
        statuses = new Status[]{Status.setup, Status.in_production, Status.over_production, Status.ending_production};
        operation = new Operation(1,"store", Status.planned);


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
    public void testGetAllMachinesWithSetupAndInOverEndingProductionStatus() {

        Mockito.when(operationRepository.findOperationById(1))
                .thenReturn(this.operation);

        when(mapper.operationToOperationDTOWithMaterialMachine(this.operation)).thenCallRealMethod();

        OperationDTOWithMaterialMachine responce = operationService.getOperationById(1);

        assertEquals(responce.getName(),"store");
    }


}