package com.java.main.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name="measured_value")
@Entity
@Getter
@Setter
public class MeasuredValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="value")
    private int value;

    @Column(name="unit")
    private String unit;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="material_id")
    private Material material;

    public MeasuredValue(){}
    public MeasuredValue(int value, String unit) {
        this.value = value;
        this.unit = unit;
    }

}

