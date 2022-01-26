package com.java.main.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.java.main.cache.helpers.CacheableElement;
import com.java.main.models.enums.LocationType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Location implements CacheableElement<Long> {

	private Long key;
	private String name;
	private boolean temp;
	private LocationType type;

	@Override
	public Long getCacheKeyElement() {
		return this.key;
	}
}
