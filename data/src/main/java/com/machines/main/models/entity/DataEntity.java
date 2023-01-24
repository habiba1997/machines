package com.machines.main.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Immutable
@Table(name = "AllData")
@Subselect("select uuid() as id, ad.* from AllData ad")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataEntity implements Serializable {

	@Id
	private String id;

	@Column(name = "machine")
	private String machine;

	@Column(name = "machine_location")
	private String machineLocation;

	@Column(name = "operation")
	private String operation;

	@Column(name = "production_order")
	private String productionOrder;

	@Column(name = "material")
	private String material;

	@Column(name = "material_amount")
	private Long materialAmount;

}
