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
 * Klasa domeny modelująca rysunek techniczny.
 * @author Arkadiusz Bojarski
 *
 */
@QueryEntity
@Entity
@Table(name = "drawing")
public class Drawing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @NotBlank
    @Column(name = "number", nullable = false, unique = true)
    private String number;

    @SuppressWarnings("unused")
    private Drawing() {
        
    }
    
    /**
     * Konstruktor przechowujący numer rysunku technicznego.
     * @param napis będący numerem rysunku dla którego powstaje obiekt klasy.
     */
    public Drawing(String number) {
        this.number = number;
    }

    /**
     * Metoda zwracająca id rysunku technicznego.
     * @return id rysunku technicznego.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Metoda zwracająca numer rysunku technicznego.
     * @return napis będący numerem rysunku technicznego.
     */
    public String getNumber() {
        return this.number;
    }

    /**
     * Metoda pozwalająca na zmianę numeru rysunku technicznego.
     * @param napis będący nowym numerem rysunku technicznego.
     */
    public void setNumber(String number) {
        this.number = number;
    }
}

