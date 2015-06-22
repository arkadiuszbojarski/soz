package org.bojarski.sozz.model.domain.dimension;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.bojarski.sozz.model.domain.unit.Unit;
import org.hibernate.validator.constraints.NotBlank;

import com.google.common.base.MoreObjects;
import com.mysema.query.annotations.QueryEmbeddable;

@QueryEmbeddable
@Embeddable
public class Dimension {
    
    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;
    
    @NotNull
    @Column(name = "value", nullable = false)
    private Double value;

    @NotNull
    @Valid
    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Unit unit;
    
    @SuppressWarnings("unused")
    private Dimension() {

    }

    public Dimension(String name, Double value, Unit unit) {
        this.name = name;
        this.value = value;
        this.unit = unit;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return this.value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Unit getUnit() {
        return this.unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
    
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("value", value)
                .add("unit", unit)
                .toString();
    }
    
}
