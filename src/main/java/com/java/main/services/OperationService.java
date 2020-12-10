package com.java.main.services;

import com.java.main.mappers.OperationMapper;
import com.java.main.models.Status;
import com.java.main.models.dtos.OperationDTO;
import com.java.main.models.dtos.operation.OperationDTOWithMaterialMachine;
import com.java.main.repositories.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OperationService {

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private OperationMapper mapper;

    @Cacheable("operationSpecifiedProductionOrder")
    public Set<OperationDTO> getAllOperationsWithSetupAndInOverEndingProductionStatus() {
        Status[] statuses = new Status[]{Status.setup, Status.in_production, Status.over_production, Status.ending_production};
        return mapper.mapOperationsTOSetOfOperationDTOWithMaterialMachine(operationRepository.findByStatusIn(statuses));
    }

    @Cacheable("operationById")
    public OperationDTOWithMaterialMachine getOperationById(int id) {
        return mapper.operationToOperationDTOWithMaterialMachine(operationRepository.findOperationById(id));
    }

}
