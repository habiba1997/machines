package com.machines.main.request;

import com.machines.main.models.helpers.MeasuredValue;
import com.machines.main.models.helpers.MesUnit;

public class MeasuredValueUtils {
	public static MeasuredValue convertToMeasuredValue(final MeasuredValueRequest measuredValueRequest) {
		if (measuredValueRequest == null) {
			return null;
		}
		return new MeasuredValue(measuredValueRequest.getValue(), MesUnit.valueOf(measuredValueRequest.getUnit()));
	}

	public static MeasuredValueRequest convertToMeasuredValueRequest(final MeasuredValue measuredValue) {
		return new MeasuredValueRequest(measuredValue.getUnit().getUnit(), measuredValue.getValue());
	}
}
