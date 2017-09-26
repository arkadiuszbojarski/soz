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

/**
 * Klasa domeny modelująca wymiar części.
 * @author Arkadiusz Bojarski
 *
 */
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

    /**
     * Konstruktor przechowujący nazwę, wartość i jednostkę miary wymiaru.
     * @param napis będący nazwą wymiaru.
     * @param liczbowa wartość wymiaru o podwójnej precyzji.
     * @param {@link Unit} jednostka miary wymiaru.
     */
    public Dimension(String name, Double value, Unit unit) {
        this.name = name;
        this.value = value;
        this.unit = unit;
    }

    /**
     * Metoda zwracająca nazwę wymiaru.
     * @return napis będący nazwą wymiaru.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Metoda pozwalająca na zmianę nazwy wymiaru.
     * @param napis będący nową nazwą wymiaru.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Metoda zwracająca wartość wymiaru.
     * @return zmiennoprzecinoka wartość liczbowa o podwójnej precyzji będąca wartością wymiaru.
     */
    public Double getValue() {
        return this.value;
    }

    /**
     * Metoda pozwalająca na zmianę wartości wymiaru.
     * @param zmiennoprzecinkowa wartość liczbowa o podwójnej precyzji będąca nową wartością wymiaru.
     */
    public void setValue(Double value) {
        this.value = value;
    }

    /**
     * Metoda zwracająca jednostkę wymiaru.
     * @return jednostka wymiaru.
     */
    public Unit getUnit() {
        return this.unit;
    }

    /**
     * Metoda pozwalająca na zmianę jednostki wymiaru.
     * @param nowa jednostka wymiaru.
     */
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
