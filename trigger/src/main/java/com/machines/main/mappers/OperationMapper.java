package com.machines.main.mappers;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import com.machines.main.converters.ConverterEnumsClass;
import com.machines.main.dtos.Operation;
import com.machines.main.models.entity.OperationEntity;
import com.machines.main.repositories.ProductionOrderRepository;

@Mapper(componentModel = "spring", uses = { MaterialMapper.class, OrderMapper.class }, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class OperationMapper implements ModelMapper<OperationEntity, Operation>, EntityMapper<OperationEntity, Operation> {

	@Autowired
	private ProductionOrderRepository productionOrderRepository;

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

	@Override
	@Mappings({
			@Mapping(target = "status", ignore = true),
			@Mapping(target = "key", ignore = true),
			@Mapping(target = "materialEntity", ignore = true),
			@Mapping(target = "productionOrderEntity", ignore = true),
			@Mapping(target = "machineEntity", ignore = true),

	})
	public abstract OperationEntity toEntity(Operation model);

	@AfterMapping
	void mapOperationToOperationEntity(@MappingTarget final OperationEntity.OperationEntityBuilder entity, final Operation operation) {
		entity
				.status(ConverterEnumsClass.OPERATION_STATUS.toEntity(operation.getStatus()))
				.productionOrderEntity(productionOrderRepository.findByName(operation.getProductionOrder().getName()));

	}
}
