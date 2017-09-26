package org.bojarski.sozz.model.domain.part;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;

import org.bojarski.sozz.model.domain.category.Category;
import org.bojarski.sozz.model.domain.dimension.Dimension;
import org.bojarski.sozz.model.domain.supplier.Supplier;
import org.hibernate.validator.constraints.NotBlank;

import com.querydsl.core.annotations.QueryEntity;
import com.querydsl.core.annotations.QueryInit;

/**
 * Klasa domeny modelująca część.
 * @author Arkadiusz Bojarski
 *
 */
@QueryEntity
@Entity
@Table(name = "part")
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long  id;

    @NotBlank
    @Column(name = "number", nullable = false, unique = true)
    private String number;

    @Column(name = "description")
    private String description;

    @Valid
    @ManyToOne(optional = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "category", referencedColumnName = "id")
    private Category category;

    @Valid
    @ElementCollection
    @CollectionTable(
            name = "part_dimensions",
            joinColumns = @JoinColumn(name = "part_id")
    )
    private Collection<Dimension> dimensions;

    @Column(name = "material")
    private String material;

    @Valid
    @QueryInit("contact.*")
    @JoinColumn(name = "supplier", referencedColumnName = "id")
    @ManyToOne(optional = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Supplier supplier;
    
    @SuppressWarnings("unused")
    private Part() {
        
    }
    
    /**
     * Konstruktor przechowujący dane części.
     * @param napis będący numerem katalogowym części.
     * @param napis będący opisem części.
     * @param {@link Category} kategoria części.
     * @param lista {@link Dimension} wymiarów części.
     * @param napis określający materiał części.
     * @param {@link Supplier} dostawca części.
     */
    public Part(String number, String description, Category category,
            Collection<Dimension> dimensions, String material, Supplier supplier) {
        this.number = number;
        this.description = description;
        this.category = category;
        this.dimensions = dimensions;
        this.material = material;
        this.supplier = supplier;
    }
    
    /**
     * Metoda zwracająca numer katalogowy części.
     * @return napis będący numerem katalogowym części.
     */
    public String getNumber() {
        return this.number;
    }

    /**
     * Metoda pozwalająca na zmianę numeru katalogowego części.
     * @param napis będący nowym numerem katalogowym części.
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Metoda zwracająca opis części.
     * @return napis będący opisem części.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Metoda pozwalająca na zmianę opisu części.
     * @param napis będący nowym opisem części.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Metoda zwracająca kategorię części.
     * @return kategoria części.
     */
    public Category getCategory() {
        return this.category;
    }

    /**
     * Metoda pozwalająca na zmianę kategorii części.
     * @param nowa kategoria części.
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Metoda zwracająca kolekcję wymiarów części.
     * @return kolekcja wymiarów części.
     */
    public Collection<Dimension> getDimensions() {
        return this.dimensions;
    }

    /**
     * Metoda pozwalająca na zmianę kolekcji wymiarów części.
     * @param nowa kolekcja wymiarów części.
     */
    public void setDimensions(Collection<Dimension> dimensions) {
        this.dimensions = dimensions;
    }

    /**
     * Metoda zwracająca materiał części.
     * @return napis będący materiałem części.
     */
    public String getMaterial() {
        return this.material;
    }

    /**
     * Metoda pozwalająca na zmianę materiału części.
     * @param napis będący 
     */
    public void setMaterial(String material) {
        this.material = material;
    }

    /**
     * Metoda wracająca dostawcę części.
     * @return dostawca części.
     */
    public Supplier getSupplier() {
        return this.supplier;
    }

    /**
     * Metoda pozwalająca na zmianę dostawcy części.
     * @param nowy dostawca części.
     */
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    /**
     * Metoda zwracająca id części.
     * @return id części.
     */
    public Long getId() {
        return this.id;
    }
}
