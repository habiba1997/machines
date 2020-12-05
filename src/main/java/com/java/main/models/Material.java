package com.java.main.models;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.io.Serializable;

@Entity
@Table(name="material")
@Cache(usage= CacheConcurrencyStrategy.READ_ONLY)
public class Material  implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @ManyToMany
    @JoinTable(
            name="materials_operations",
            joinColumns = @JoinColumn(name="material_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="operation_id", referencedColumnName = "id")
    )
    private Set<Operation> operations= new HashSet<>();


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

    public Set<Operation> getOperations() {
        return operations;
    }

    public void setOperations(Set<Operation> operations) {
        this.operations = operations;
    }

    public MeasuredValue getMeasuredValue() {
        return measuredValue;
    }

    public void setMeasuredValue(MeasuredValue measuredValue) {
        this.measuredValue = measuredValue;
    }

    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

