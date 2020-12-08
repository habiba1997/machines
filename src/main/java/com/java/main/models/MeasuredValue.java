package com.java.main.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name="measured_value")
@Entity
@Getter @Setter @NoArgsConstructor
public class MeasuredValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="value")
    private int value;

    @Column(name="unit")
    private String unit;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="material_id")
    private Material material;

    public MeasuredValue(int value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof MeasuredValue)) {
            return false;
        } else {
            MeasuredValue other = (MeasuredValue)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getId() != other.getId()) {
                return false;
            } else if (this.getValue() != other.getValue()) {
                return false;
            } else {
                label40: {
                    Object this$unit = this.getUnit();
                    Object other$unit = other.getUnit();
                    if (this$unit == null) {
                        if (other$unit == null) {
                            break label40;
                        }
                    } else if (this$unit.equals(other$unit)) {
                        break label40;
                    }

                    return false;
                }

                Object this$material = this.getMaterial();
                Object other$material = other.getMaterial();
                if (this$material == null) {
                    if (other$material != null) {
                        return false;
                    }
                } else if (!this$material.equals(other$material)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof MeasuredValue;
    }


    public String toString() {
        return "MeasuredValue(id=" + this.getId() + ", value=" + this.getValue() + ", unit=" + this.getUnit() + ", material=" + this.getMaterial() + ")";
    }

}

