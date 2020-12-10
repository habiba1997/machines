package com.java.main.models.dtos.operation;

import com.java.main.models.dtos.MeasuredValueDTO;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
public class MaterialDTONoOperation {

    private int id;
    private String name;
    private MeasuredValueDTO measuredValue;
    private boolean percentageColor;

    public MaterialDTONoOperation(int id, String name, MeasuredValueDTO measuredValue, boolean percentageColor) {
        this.id = id;
        this.name = name;
        this.measuredValue = measuredValue;
        this.percentageColor = percentageColor;
    }
}
