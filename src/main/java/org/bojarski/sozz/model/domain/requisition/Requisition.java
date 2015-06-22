package org.bojarski.sozz.model.domain.requisition;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.bojarski.sozz.model.domain.account.Account;
import org.bojarski.sozz.model.domain.drawing.Drawing;
import org.bojarski.sozz.model.domain.part.Part;
import org.bojarski.sozz.model.domain.unit.Unit;

import com.mysema.query.annotations.QueryEntity;
import com.mysema.query.annotations.QueryInit;

@QueryEntity
@Entity
@EntityListeners(value = {RequisitionEntityListener.class})
@Table(name = "requisition")
public class Requisition {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    
    @Column(name = "number", nullable = false, updatable = false)
    private Long number;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start", nullable = false, updatable = false)
    private Date start;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end", nullable = true)
    private Date end;

    @ManyToOne
    @JoinColumn(name = "author", nullable = false, updatable = false)
    private Account author;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "element")
    private String element;

    @NotNull
    @Valid
    @QueryInit("*.*.*")
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "part", nullable = false, referencedColumnName = "id")
    private Part part;

    @NotNull
    @Column(name = "amount", nullable = false, precision = 10, scale = 4)
    private Double amount;

    @NotNull
    @Valid
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "unit", nullable = false, referencedColumnName = "id")
    private Unit unit;

    @NotNull
    @Valid
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "drawing", nullable = false, referencedColumnName = "id")
    private Drawing drawing;

    @Column(name = "comment")
    private String comment;

    @SuppressWarnings("unused")
    private Requisition() {
        
    }
    
    public Requisition(Long number, Status status, String element, Part part,
            Double amount, Unit unit, Drawing drawing, String comment) {
        this.number = number;
        this.status = status;
        this.element = element;
        this.part = part;
        this.amount = amount;
        this.unit = unit;
        this.drawing = drawing;
        this.comment = comment;
    }

    public Long getNumber() {
        return this.number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Date getStart() {
        return this.start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return this.end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Account getAuthor() {
        return this.author;
    }

    public void setAuthor(Account author) {
        this.author = author;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getElement() {
        return this.element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public Part getPart() {
        return this.part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Unit getUnit() {
        return this.unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Drawing getDrawing() {
        return this.drawing;
    }

    public void setDrawing(Drawing drawing) {
        this.drawing = drawing;
    }

    public Long getId() {
        return this.id;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
}
