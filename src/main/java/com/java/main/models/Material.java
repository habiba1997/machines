package com.java.main.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@Builder
@Table(name="material")
@Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
@Data @NoArgsConstructor
@AllArgsConstructor
public class Material  implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "material")
    private Set<Operation> operations= new HashSet<>();


    @OneToOne(mappedBy = "material", fetch = FetchType.LAZY)
    private MeasuredValue measuredValue;

    @Column(name="percentage_color")
    private boolean percentageColor;

    public Material(String name) {
        this.name = name;
    }

    public Material(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Material(int id, String name, MeasuredValue measuredValue, boolean percentageColor) {
        this.id = id;
        this.name = name;
        this.measuredValue = measuredValue;
        this.percentageColor = percentageColor;
    }

    public void addOperation(Operation operation) {
        this.operations.add(operation);
    }
}

