package com.java.main.models.dtos;


import lombok.Data;
import lombok.NoArgsConstructor;
import com.java.main.models.Status;


@Data
@NoArgsConstructor
public class OperationDTO {
    private int id;
    private String name;
    private Status status;

    public OperationDTO(int id, String name, Status status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }
}
