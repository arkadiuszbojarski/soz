package org.bojarski.sozz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import com.querydsl.core.annotations.QueryEntity;

/**
 * Klasa dziedziny modelująca jednostkę miary.
 * @author Arkadiusz Bojarski
 */
@QueryEntity
@Entity
@Table(name = "unit")
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @NotBlank
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @SuppressWarnings("unused")
    private Unit() {

    }

    /**
     * Konstruktor przechowujący skrótową nazwę jednostki miary.
     * @param name
     */
    public Unit(String name) {
        this.name = name;
    }

    /**
     * Metoda zwracająca id jednostki miary.
     * @return
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Metoda ustawiająca skrótową nazwę jednostki miary.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Metoda zwracająca nazwę jednostki miary.
     * @return
     */
    public String getName() {
        return this.name;
    }
}
