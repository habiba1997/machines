package com.java.main.models;

import javax.persistence.*;

import lombok.*;

@Table(name = "operation")
@Entity
@Data
@Builder
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class Operation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@JoinColumn(name = "machine_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Machine machine;

	@JoinColumn(name = "material_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Material material;

	@Column(name = "status")
	private Status status;

}
