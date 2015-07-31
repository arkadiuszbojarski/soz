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

import com.google.common.base.MoreObjects;
import com.mysema.query.annotations.QueryEntity;
import com.mysema.query.annotations.QueryInit;

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
    
    public Part(String number, String description, Category category,
            Collection<Dimension> dimensions, String material, Supplier supplier) {
        this.number = number;
        this.description = description;
        this.category = category;
        this.dimensions = dimensions;
        this.material = material;
        this.supplier = supplier;
    }
    
    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Collection<Dimension> getDimensions() {
        return this.dimensions;
    }

    public void setDimensions(Collection<Dimension> dimensions) {
        this.dimensions = dimensions;
    }

    public String getMaterial() {
        return this.material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Supplier getSupplier() {
        return this.supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("number", number)
                .add("description", description)
                .add("category", category)
                .add("dimensions", dimensions)
                .add("material", material)
                .add("supplier", supplier)
                .toString();
    }
    
}
