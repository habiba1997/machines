package com.machines.main.dtos;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.machines.main.cache.CacheableElement;
import com.machines.main.models.enums.MachineType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Machine implements CacheableElement<String> {
	private String name;
	private MachineType machineType;
	private Location location;

	@Override
	public String getCacheKeyElement() {
		return this.name;
	}

	public Optional<Location> getLocation() {
		return Optional.ofNullable(location);
	}
}
