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
@NoArgsConstructor
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
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Operation)) {
            return false;
        } else {
            Operation other = (Operation)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getId() != other.getId()) {
                return false;
            } else {
                label49: {
                    Object this$name = this.getName();
                    Object other$name = other.getName();
                    if (this$name == null) {
                        if (other$name == null) {
                            break label49;
                        }
                    } else if (this$name.equals(other$name)) {
                        break label49;
                    }

                    return false;
                }

                Object this$machine = this.getMachine();
                Object other$machine = other.getMachine();
                if (this$machine == null) {
                    if (other$machine != null) {
                        return false;
                    }
                } else if (!this$machine.equals(other$machine)) {
                    return false;
                }

                Object this$materials = this.getMaterials();
                Object other$materials = other.getMaterials();
                if (this$materials == null) {
                    if (other$materials != null) {
                        return false;
                    }
                } else if (!this$materials.equals(other$materials)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Operation;
    }

//    public int hashCode() {
//        boolean PRIME = true;
//        int result = 1;
//        result = result * 59 + this.getId();
//        Object $name = this.getName();
//        result = result * 59 + ($name == null ? 43 : $name.hashCode());
//        Object $machine = this.getMachine();
//        result = result * 59 + ($machine == null ? 43 : $machine.hashCode());
//        Object $materials = this.getMaterials();
//        result = result * 59 + ($materials == null ? 43 : $materials.hashCode());
//        return result;
//    }

    public String toString() {
        return "Operation(id=" + this.getId() + ", name=" + this.getName() + ", machine=" + this.getMachine() + ", materials=" + this.getMaterials() + ")";
    }

}
