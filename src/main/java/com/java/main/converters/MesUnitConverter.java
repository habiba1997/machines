package com.java.main.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.java.main.models.helpers.MesUnit;

@Converter(autoApply = true)
public class MesUnitConverter implements AttributeConverter<MesUnit, String> {

	@Override
	public String convertToDatabaseColumn(final MesUnit attribute) {
		if (attribute == null) {
			return null;
		}
		return attribute.getUnit();
	}

	@Override
	public MesUnit convertToEntityAttribute(final String dbData) {
		if (dbData == null) {
			return null;
		}
		return MesUnit.valueOf(dbData);
	}
}
