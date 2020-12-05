package com.java.main.models.dtos;

import java.util.HashSet;
import java.util.Set;


public class MachineDTO {
    private int id;
    private String name;
    private Set<OperationDTO> operations = new HashSet<>();

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
}

