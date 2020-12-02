package com.java.main.models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name="material")
@Entity
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @ManyToMany
    @JoinTable(
            name="materials_operations",
            joinColumns = @JoinColumn(name="material_id"),
            inverseJoinColumns = @JoinColumn(name="operation_id")
    )
    private List<Operation> operations= new ArrayList<Operation>();


    @OneToOne(mappedBy = "material")
    private MeasuredValue measuredValue;

    public Material(){}
    public Material(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public MeasuredValue getMeasuredValue() {
        return measuredValue;
    }

    public void setMeasuredValue(MeasuredValue measuredValue) {
        this.measuredValue = measuredValue;
    }
}

