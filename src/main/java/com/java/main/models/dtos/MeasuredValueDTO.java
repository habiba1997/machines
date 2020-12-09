package com.java.main.models.dtos;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MeasuredValueDTO {

    private int value;
    private String unit;


    public MeasuredValueDTO(int value, String unit) {
        this.value = value;
        this.unit = unit;
    }

}

