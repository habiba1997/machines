package com.java.main.models;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


import java.io.Serializable;
import java.util.Set;

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

    @OneToMany(mappedBy = "machine")
    private Set<Operation> operations = new HashSet<Operation>();

    public Machine() { }

    public Machine(String name) {
        this.name = name;
    }
    public Machine(int id, String name) {
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

    public Set<Operation> getOperations() {
        return operations;
    }

    public void setOperations(Set<Operation> operations) {
        this.operations = operations;
    }

    public void addOperation(Operation operation) {
        this.operations.add(operation);
    }
}

