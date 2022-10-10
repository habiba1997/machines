package com.machines.main.mappers;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.TimeZone;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class TimeZoneMapper {

	protected ZonedDateTime mapLocalToZone(final LocalDateTime localDateTime) {
		TimeZone timezone = TimeZone.getTimeZone("Africa/Cairo");
		return ZonedDateTime.of(localDateTime, timezone.toZoneId());
	}

	protected LocalDateTime mapZoneToLocal(final ZonedDateTime zonedDateTime) {
		return LocalDateTime.from(zonedDateTime);
	}
}
