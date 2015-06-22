package org.bojarski.sozz.model.domain.drawing;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import com.google.common.base.MoreObjects;
import com.mysema.query.annotations.QueryEntity;

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
    
    public Drawing(String number) {
        this.number = number;
    }

    public Long getId() {
        return this.id;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("number", number)
                .toString();
    }
    
}

