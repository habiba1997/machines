package com.machines.main.models.enums;

import lombok.Getter;

public enum LocationType {
	IN_DOOR("in_door"),
	INVENTORY("inventory"),
	ON_DOOR("on_door"),
	FLOOR("floor");

	private @Getter String value;

	LocationType(final String value) {
		this.value = value;
	}

	public static LocationType getLocationType(final String typeString) {
		for (LocationType type : LocationType.values()) {
			if (type.getValue().equals(typeString)) {
				return type;
			}
		}
		return null;
	}
}
