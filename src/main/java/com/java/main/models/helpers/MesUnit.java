package com.java.main.models.helpers;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import org.apache.commons.lang3.Validate;

@Getter
@ToString
@EqualsAndHashCode(of = "unit")
public class MesUnit implements Serializable {

	private static Map<String, MesUnit> mesUnits = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

	public static final MesUnit METER = new MesUnit("m");
	public static final MesUnit PC = new MesUnit("PC");
	public static final MesUnit KILO_GRAM = new MesUnit("kg");
	public static final MesUnit GRAM = new MesUnit("g", KILO_GRAM, "0.001");
	public static final MesUnit SECOND = new MesUnit("S");
	public static final MesUnit TS = new MesUnit("TS", SECOND, "1000"); // thousand

	private final String unit;
	private final String baseUnit;
	private final BigDecimal baseUnitConversion;

	/**
	 * Create a base unit MesUnit.
	 *
	 * @param unit like "kg" or "m".
	 */
	private MesUnit(final String unit) {
		Validate.notEmpty(unit);
		this.unit = unit;
		this.baseUnit = unit;
		this.baseUnitConversion = new BigDecimal("1");
	}

	private MesUnit(final String unit, final String baseUnit, final String baseUnitConversion) {
		Validate.notEmpty(unit);
		Validate.notEmpty(baseUnit);
		this.unit = unit;
		this.baseUnit = baseUnit;
		this.baseUnitConversion = new BigDecimal(baseUnitConversion);
	}

	private MesUnit(final String unit, final MesUnit baseUnit, final String baseUnitConversion) {
		Validate.notEmpty(unit);
		this.unit = unit;
		this.baseUnit = baseUnit.getBaseUnit();
		this.baseUnitConversion = new BigDecimal(baseUnitConversion);
	}


	public static MesUnit valueOf(final String unit) {
		if (mesUnits.containsKey(unit)) {
			return mesUnits.get(unit);
		} else {
			return new MesUnit(unit);
		}
	}

	static {
		// On startup, we load all constant in the map
		try {
			for (Field f : MesUnit.class.getDeclaredFields()) {
				if (f.getType() == MesUnit.class && Modifier.isStatic(f.getModifiers())) {
					f.setAccessible(true);
					addStaticMesUnit((MesUnit) f.get(null));
				}
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	private static void addStaticMesUnit(final MesUnit mesUnit) {
		mesUnits.put(mesUnit.getUnit(), mesUnit);
	}

}
