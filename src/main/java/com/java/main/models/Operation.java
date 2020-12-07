package com.java.main.models;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(name="operation")
@Entity
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

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public Set<Material> getMaterials() {
        return materials;
    }

    public void addMaterials(Material material) {
        this.materials.add(material);
    }

}
