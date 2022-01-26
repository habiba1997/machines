package com.java.main.models.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;

import com.java.main.converters.MeasureValueConverter;
import com.java.main.models.helpers.MeasuredValue;

@Entity
@Table(name = "production_order")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Data
@Builder
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class ProductionOrderEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long key;

	// Hibernate 4 has bring lots of improvements and @NaturalId is one of such nice improvements.
	// As you know @Id annotation is used as meta data for specifying the primary key of an entity.
	// But sometimes, entity is usually used in DAO layer code with id which not not primary key but its logical or natural id.
	// In such cases, @NaturalId annotation will prove good replacement of named queries in hibernate.
	@NaturalId
	@Column(name = "name")
	private String name;

	@Column(name = "start_date")
	private LocalDateTime startDate;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "material_key", unique = true)
	private MaterialEntity materialEntity;

	@Column(name = "planned_quantity")
	@Convert(converter = MeasureValueConverter.class)
	private MeasuredValue plannedQuantity;

	// Collections are not cached by default, and we need to explicitly mark them as cacheable.
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@OneToMany(mappedBy = "productionOrderEntity", fetch = FetchType.EAGER)
	private List<OperationEntity> operationEntityList;

}
//NaturalId annotation
//Note:
//		If you look at logs then you will know that when you get an entity by its natural id then
//
//		First primary key of entity is found by executing where clause of natural id
//		This primary key is used fetch the information of entity