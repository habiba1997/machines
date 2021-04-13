package com.java.main.models;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(name="operation")
@Entity
@Data
@Builder
@EqualsAndHashCode(of="id")
@NoArgsConstructor
@AllArgsConstructor
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

    @JoinColumn(name="material_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Material material;

    @Column(name="status")
    private Status status;

    public Operation(String name) {
        this.name = name;
    }
    public Operation(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Operation(int id, String name, Material material, Status status) {
        this.id = id;
        this.name = name;
        this.machine = machine;
        this.material = material;
        this.status = status;
    }

    public Operation(int id, String name, Status status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }
}
