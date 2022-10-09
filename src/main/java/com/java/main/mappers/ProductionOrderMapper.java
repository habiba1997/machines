package com.java.main.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import com.java.main.dtos.ProductionOrder;
import com.java.main.models.entity.ProductionOrderEntity;

@Mapper(componentModel = "spring", uses = { MaterialMapper.class, OperationMapper.class, TimeZoneMapper.class
}, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductionOrderMapper extends ModelMapper<ProductionOrderEntity, ProductionOrder>, EntityMapper<ProductionOrderEntity, ProductionOrder> {

	@Mappings({
			@Mapping(source = "materialEntity", target = "material"),
			@Mapping(source = "operationEntityList", target = "operationList")
	})
	ProductionOrder toModel(ProductionOrderEntity entity);

	@Mappings({
			@Mapping(target = "materialEntity", ignore = true),
			@Mapping(source = "operationList", target = "operationEntityList"),
			@Mapping(target = "key", ignore = true),
	})
	ProductionOrderEntity toEntity(ProductionOrder model);
}
