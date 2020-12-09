package com.java.main.models.dtos;


import com.java.main.models.Material;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.java.main.models.Status;


@Data
@NoArgsConstructor
public class OperationDTO {
    private int id;
    private String name;
//    private Material material;
    private Status status;
}
