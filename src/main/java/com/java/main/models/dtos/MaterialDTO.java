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


    public MaterialDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

}

