package com.java.main.models.dtos;

import java.util.HashSet;
import java.util.Set;

public class MaterialDTO {

    private int id;
    private String name;
    private Set<OperationDTO> operations = new HashSet<>();
    private MeasuredValueDTO measuredValue;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<OperationDTO> getOperations() {
        return operations;
    }

    public void setOperations(Set<OperationDTO> operations) {
        this.operations = operations;
    }

    public MeasuredValueDTO getMeasuredValue() {
        return measuredValue;
    }

    public void setMeasuredValue(MeasuredValueDTO measuredValue) {
        this.measuredValue = measuredValue;
    }
}

