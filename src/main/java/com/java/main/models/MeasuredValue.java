package com.java.main.models;

import javax.persistence.*;

import lombok.*;

@Table(name = "measured_value")
@Entity
@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class MeasuredValue {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "value")
	private int value;

	@Column(name = "unit")
	private String unit;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "material_id")
	private Material material;

	public MeasuredValue(final int value, final String unit) {
		this.value = value;
		this.unit = unit;
	}
}
