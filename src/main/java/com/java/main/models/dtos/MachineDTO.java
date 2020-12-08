package com.java.main.models.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class MachineDTO {
    private int id;
    private String name;
    private Set<OperationDTO> operations = new HashSet<>();


    public MachineDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

