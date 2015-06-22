package org.bojarski.sozz.model.domain.supplier;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;

import org.bojarski.sozz.model.domain.contact.Contact;
import org.hibernate.validator.constraints.NotBlank;

import com.google.common.base.MoreObjects;
import com.mysema.query.annotations.QueryEntity;

@QueryEntity
@Entity
@Table(name = "supplier")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @NotBlank
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Valid
    @Embedded
    Contact contact;
    
    @SuppressWarnings("unused")
    private Supplier() {
        
    }

    public Supplier(String name, Contact contact) {
        this.name = name;
        this.contact = contact;
    }
    
    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Contact getContact() {
        return this.contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("contact", contact)
                .toString();
    }
    
}
