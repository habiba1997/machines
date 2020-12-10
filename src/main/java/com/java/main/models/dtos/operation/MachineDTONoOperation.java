package com.java.main.models.dtos.operation;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MachineDTONoOperation {

    private int id;
    private String name;

    public MachineDTONoOperation(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
