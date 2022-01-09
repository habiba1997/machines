package com.java.main.mappers;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.TimeZone;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class TimeZoneMapper {
	protected ZonedDateTime map(final LocalDateTime value) {
		TimeZone timezone = TimeZone.getTimeZone("Africa/Cairo");
		return ZonedDateTime.of(value, timezone.toZoneId());
	}
}
