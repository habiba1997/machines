package com.java.main.mappers;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import com.java.main.converters.ConverterEnumsClass;
import com.java.main.dtos.Operation;
import com.java.main.models.entity.OperationEntity;

@Mapper(componentModel = "spring", uses = { MaterialMapper.class, OrderMapper.class }, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class OperationMapper implements ModelMapper<OperationEntity, Operation> {

	@Override
	@Mappings({
			@Mapping(target = "status", ignore = true),
			@Mapping(source = "materialEntity", target = "material"),
			@Mapping(source = "productionOrderEntity", target = "productionOrder")
	})
	public abstract Operation toModel(OperationEntity entity);

	@AfterMapping
	public void fillStatus(final OperationEntity entity, @MappingTarget final Operation.OperationBuilder operation) {
		operation.status(ConverterEnumsClass.OPERATION_STATUS.toModel(entity.getStatus()));
	}

//	@Override
//	@Mappings({
//			@Mapping(target = "status", ignore = true),
//			@Mapping(source = "material", target = "materialEntity"),
//			@Mapping(source = "productionOrder", target = "productionOrderEntity")
//	})
//	public abstract OperationEntity toEntity(Operation model);
//
//	@AfterMapping
//	void mapOperationToOperationEntity(@MappingTarget final OperationEntity entity, final Operation operation) {
//		entity.setStatus(ConverterEnumsClass.operationStatus.toEntity(operation.getStatus()));
//	}
}
