package com.java.main.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class BooleanConverter implements AttributeConverter<Boolean, String> {

	@Override
	public String convertToDatabaseColumn(final Boolean value) {
		return (value != null && value) ? "1" : "0";
	}

	@Override
	public Boolean convertToEntityAttribute(final String value) {
		return "1".equals(value);
	}
}