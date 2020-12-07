package com.java.main.models;

import javax.persistence.*;

@Table(name="measured_value")
@Entity
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}

