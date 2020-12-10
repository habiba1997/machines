package com.java.main.models.dtos.operation;

import com.java.main.models.Status;
import com.java.main.models.dtos.MachineDTO;
import com.java.main.models.dtos.MaterialDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class OperationDTOWithMaterialMachine {

        private int id;
        private String name;
        private MachineDTONoOperation machine;
        private MaterialDTONoOperation material;
        private Status status;


}
