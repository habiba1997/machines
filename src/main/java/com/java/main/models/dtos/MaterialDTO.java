package com.java.main.models.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
public class MaterialDTO {

    private int id;
    private String name;
    private Set<OperationDTO> operations = new HashSet<>();
    private MeasuredValueDTO measuredValue;
    private boolean percentageColor;


    public MaterialDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public MaterialDTO(int id, String name, MeasuredValueDTO measuredValue, boolean percentageColor) {
        this.id = id;
        this.name = name;
        this.measuredValue = measuredValue;
        this.percentageColor = percentageColor;
    }
    public void addOperations(OperationDTO operationDTO)
    {
        operations.add(operationDTO);
    }
}

