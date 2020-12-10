package com.java.main.mappers;

import com.java.main.models.Material;
import com.java.main.models.MeasuredValue;
import com.java.main.models.Operation;
import com.java.main.models.dtos.MaterialDTO;
import com.java.main.models.dtos.MeasuredValueDTO;
import com.java.main.models.dtos.OperationDTO;
import org.mapstruct.*;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface MaterialMapper {

    MeasuredValueDTO measuredValueToDto(MeasuredValue measuredValue);

    MaterialDTO materialToDto(Material material);


    OperationDTO operationToDto(Operation operation);

    Set<MaterialDTO> mapMaterialSetToMaterialDtoSet(Set<Material> materials);
}