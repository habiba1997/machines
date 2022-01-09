package com.java.main.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.java.main.models.enums.LocationType;

@Converter(autoApply = true)
public class LocationTypeEnumConverter implements AttributeConverter<LocationType, String> {
	@Override
	public String convertToDatabaseColumn(final LocationType attribute) {
		if (attribute == null) {
			return null;
		}
		return attribute.getValue();
	}

	@Override
	public LocationType convertToEntityAttribute(final String dbData) {
		if (dbData == null) {
			return null;
		}
		return LocationType.getLocationType(dbData);
	}
}
