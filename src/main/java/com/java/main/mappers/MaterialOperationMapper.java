package com.java.main.mappers;

import com.java.main.models.Material;
import com.java.main.models.MeasuredValue;
import com.java.main.models.Operation;
import com.java.main.models.dtos.MaterialDTO;
import com.java.main.models.dtos.MeasuredValueDTO;
import com.java.main.models.dtos.OperationDTO;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface MaterialOperationMapper {
    Operation dtoToOperation(OperationDTO operationDTO);

    OperationDTO operationToDto(Operation operation);

    MeasuredValueDTO measuredValueToDto(MeasuredValue measuredValue);

    MeasuredValue DtoToMeasuredValue(MeasuredValueDTO dto);

    Set<MaterialDTO> mapMaterialSetToMaterialDtoSet(Set<Material> materials);
}
