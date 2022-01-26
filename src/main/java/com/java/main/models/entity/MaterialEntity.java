package com.java.main.models.entity;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;

import com.java.main.converters.MeasureValueConverter;
import com.java.main.converters.MesUnitConverter;
import com.java.main.models.helpers.MeasuredValue;
import com.java.main.models.helpers.MesUnit;

@Entity
@Builder
@Table(name = "material")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long key;

	@NaturalId
	@Column(name = "name")
	private String name;

	@Column(name = "measure_value")
	@Convert(converter = MeasureValueConverter.class)
	private MeasuredValue measuredValue;

	@Column(name = "base_uom")
	@Convert(converter = MesUnitConverter.class)
	private MesUnit baseUnit;

}
