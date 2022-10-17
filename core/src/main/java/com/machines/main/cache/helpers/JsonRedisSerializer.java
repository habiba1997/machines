package com.machines.main.cache.helpers;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.AllArgsConstructor;
import lombok.Getter;

import org.redisson.spring.cache.NullValue;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.util.ClassUtils;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.machines.main.models.helpers.MeasuredValue;
import com.machines.main.models.helpers.MesUnit;

@ConditionalOnProperty(name = "redis.cache.enabled", havingValue = "true")
public class JsonRedisSerializer implements RedisSerializer<Object> {

	private ObjectMapper mapper;
	static final byte[] EMPTY_ARRAY = new byte[0];

	@AllArgsConstructor
	@Getter
	class Dataum {
		Class<?> tgtClass;
		String[] ignorableProperties;
	}

	private Map<Class<?>, Dataum> classMap = new ConcurrentHashMap<>();

	public JsonRedisSerializer() {
		this.mapper = new ObjectMapper();
		this.mapper = mapper.registerModule(new SimpleModule().addSerializer(new NullValueSerializer()));
		this.mapper = mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.registerModule(new Jdk8Module());
		mapper.registerModule(new JavaTimeModule());

		this.mapper = mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);

		mapper.addMixIn(MeasuredValue.class, MeasureValueMixIn.class);
		mapper.addMixIn(MesUnit.class, MesUnitMixIn.class);
	}

	public void addClassMap(Class<?> source, Class<?> tgt, String[] ignorableProperties) {
		classMap.put(source, new Dataum(tgt, ignorableProperties));
	}

	@Override
	public byte[] serialize(Object source) throws SerializationException {
		if (source == null) {
			return EMPTY_ARRAY;
		}
		try {
			return mapper.writeValueAsBytes(source);
		} catch (JsonProcessingException e) {
			throw new SerializationException("Could not write JSON: " + e.getMessage(), e);
		}
	}

	@Override
	public Object deserialize(byte[] source) throws SerializationException {
		if (isEmpty(source)) {
			return null;
		}
		try {
			Object object = mapper.readValue(source, Object.class);
			for (Map.Entry<Class<?>, Dataum> entry : classMap.entrySet()) {
				if (ClassUtils.isAssignable(entry.getKey(), object.getClass())) {
					Dataum dataum = entry.getValue();
					Object tgt = dataum.getTgtClass().newInstance();
					BeanUtils.copyProperties(object, tgt, dataum.getIgnorableProperties());
					return tgt;
				}
			}
			return object;
		} catch (Exception ex) {
			throw new SerializationException("Could not read JSON: " + ex.getMessage(), ex);
		}
	}

	private boolean isEmpty(byte[] data) {
		return (data == null || data.length == 0);
	}

	private static class NullValueSerializer extends StdSerializer<NullValue> {

		private static final long serialVersionUID = 211020517180777825L;
		private final String classIdentifier;

		NullValueSerializer() {
			super(NullValue.class);
			this.classIdentifier = "@class";
		}

		@Override
		public void serialize(
				NullValue value, JsonGenerator jsonGenerator, SerializerProvider provider)
				throws IOException {
			jsonGenerator.writeStartObject();
			jsonGenerator.writeStringField(classIdentifier, NullValue.class.getName());
			jsonGenerator.writeEndObject();
		}
	}

	private interface MeasureValueMixIn {

		@JsonValue
		String getText();
	}

	private interface MesUnitMixIn {

		@JsonValue
		String getUnit();

	}

}
