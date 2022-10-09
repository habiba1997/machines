package com.java.main.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import com.java.main.dtos.Order;
import com.java.main.models.entity.ProductionOrderEntity;

@Mapper(componentModel = "spring", uses = { MaterialMapper.class, TimeZoneMapper.class }, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface OrderMapper extends ModelMapper<ProductionOrderEntity, Order> {
	@Mappings({
			@Mapping(source = "materialEntity", target = "material")
	})
	Order toModel(ProductionOrderEntity entity);

}
