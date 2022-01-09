package com.java.main.converters;

import java.math.BigDecimal;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.java.main.models.helpers.MeasuredValue;
import com.java.main.models.helpers.MesUnit;

//If the autoApply element is specified as true, the persistence provider must automatically apply the converter to all mapped attributes
// of the specified target type for all entities in the persistence unit
// except for attributes for which conversion is overridden by means of the Convert annotation
@Converter(autoApply = true)
public class MeasureValueConverter implements AttributeConverter<MeasuredValue, String> {

	private static final String SEPARATOR = " ";

	@Override
	public String convertToDatabaseColumn(final MeasuredValue measuredValue) {
		if (measuredValue == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		sb.append(measuredValue.getValue());
		sb.append(" ");
		sb.append(measuredValue.getUnit().getUnit());
		return sb.toString();
	}

	@Override
	public MeasuredValue convertToEntityAttribute(final String measureValueString) {
		if (measureValueString == null || measureValueString.isEmpty()) {
			return null;
		}

		String[] splits = measureValueString.split(SEPARATOR);

		if (splits == null || splits.length == 0) {
			return null;
		}

		MeasuredValue.MeasuredValueBuilder measuredValue = MeasuredValue.builder();
		String value = !splits[0].isEmpty() ? splits[0] : null;
		if (measureValueString.contains(SEPARATOR)) {
			measuredValue.value(new BigDecimal(value));

			if (splits.length >= 2 && splits[1] != null && !splits[1].isEmpty()) {
				measuredValue
						.unit(MesUnit.valueOf(splits[1]))
						.baseUnitValue(BigDecimal.ONE);
			}
		} else {
			measuredValue
					.value(new BigDecimal(value))
					.unit(MesUnit.PC)
					.baseUnitValue(BigDecimal.ONE);
		}

		return measuredValue.build();
	}
}
//todo: validate