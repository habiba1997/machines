package com.java.main.models;


import lombok.Data;
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
@Data
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

    public void addOperation(Operation operation) {
        this.operations.add(operation);
    }
}

