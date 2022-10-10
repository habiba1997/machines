package com.machines.main.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import com.machines.main.dtos.Order;
import com.machines.main.models.entity.ProductionOrderEntity;

@Mapper(componentModel = "spring", uses = { MaterialMapper.class, TimeZoneMapper.class }, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface OrderMapper extends ModelMapper<ProductionOrderEntity, Order> {
	@Mappings({
			@Mapping(source = "materialEntity", target = "material")
	})
	Order toModel(ProductionOrderEntity entity);

}
