package com.java.main.models;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


import java.io.Serializable;

@Entity
@Table(name="machine")
@Cache(usage= CacheConcurrencyStrategy.READ_ONLY)
public class Machine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @OneToMany
    @JoinColumn(name="machine_id", referencedColumnName = "id")
    private List<Operation> operations = new ArrayList<Operation>();

    public Machine() { }

    public Machine(String name) {
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

    public void addOperations(Operation operation) {
        this.operations.add(operation);
    }
}

