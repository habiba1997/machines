package com.java.main.models;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(name="operation")
@Entity
@Getter @Setter
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @JoinColumn(name="machine_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Machine machine;

    @ManyToMany(mappedBy = "operations")
    private Set<Material> materials = new HashSet<>();


    public Operation(){}
    public Operation(String name) {
        this.name = name;
    }
    public Operation(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public void addMaterials(Material material) {
        this.materials.add(material);
    }

}
