package com.java.main.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "operation")
@Entity
@Data
@Builder
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class OperationEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "material_key", unique = true)
	private MaterialEntity materialEntity;

	// one-to-many mapping means that one row in a table is mapped to multiple rows in another table.
	@JoinColumn(name = "production_order_key")
	@ManyToOne(fetch = FetchType.EAGER)
	private ProductionOrderEntity productionOrderEntity;

	@Column(name = "status")
	private String status;

}