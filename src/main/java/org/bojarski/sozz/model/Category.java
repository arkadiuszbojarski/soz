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
 * Klasa dziedziny modelująca kategorie części.
 * @author Arkadiusz Bojarski
 *
 */
@QueryEntity
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @NotBlank
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    
    @SuppressWarnings("unused")
    private Category() {
        
    }
    
    /**
     * Kostruktor przechowujący nazwę kategorii.
     * @param name
     */
    public Category(String name) {
        this.name = name;
    }
    
    /**
     * Metoda zwracająca id kategorii.
     * @return numer identyfikujący kategorię.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Metoda zwracająca nazwę kategorii.
     * @return napis będący nazwą kategorii.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Metoda ustawiająca nazwę kategorii.
     * @param name napis mający stać się nazwą kategorii.
     */
    public void setName(String name) {
        this.name = name;
    }
}
