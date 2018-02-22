package org.bojarski.sozz.model;

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

import com.querydsl.core.annotations.QueryEntity;
import com.querydsl.core.annotations.QueryInit;

/**
 * Klasa domeny modelująca zapotrzebowanie.
 * @author Arkadiusz Bojarski
 *
 */
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
    
    /**
     * Konstruktor przechowujący dane zapotrzebowania.
     * @param liczbowy numer zapotrzebowania.
     * @param {@link Status} zapotrzebowania.
     * @param napis oznaczający jako jaki element część będzie wykorzystana.
     * @param {@link Part} część na jaką składane jest zapotrzebowanie.
     * @param liczbowa ilość części. 
     * @param {@link Unit} ilość części.
     * @param {@link Drawing} rysunek techniczny na podstawie którego powstaje zapotrzebowanie.
     * @param napis stanowiący dodatkowe ewentualne uwagi do zapotrzebowania.
     */
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

    /**
     * Metoda zwracająca numer zapotrzebowania.
     * @return numer zapotrzebowania.
     */
    public Long getNumber() {
        return this.number;
    }

    /**
     * Metoda pozwalająca na zmianę numeru zapotrzebowania.
     * @param nowy numer zapotrzebowania.
     */
    public void setNumber(Long number) {
        this.number = number;
    }

    /**
     * Metoda zwracająca datę utworzenia zapotrzebowania.
     * @return data utworzenia zapotrzebowania.
     */
    public Date getStart() {
        return this.start;
    }

    /**
     * Metoda pozwalająca na zmianę daty utworzenia zapotrzebowania.
     * @param nowa data utworzenia zapotrzebowania.
     */
    public void setStart(Date start) {
        this.start = start;
    }

    /**
     * Metoda zwracająca datę dezaktualizacji zapotrzebowania.
     * @return data dezaktualizacji zapotrzebowania.
     */
    public Date getEnd() {
        return this.end;
    }

    /**
     * Metoda pozwalająca na zmianę daty dezaktualizacji zapotrzebowania.
     * @param nowa data dezaktualizacji zapotrzebowania.
     */
    public void setEnd(Date end) {
        this.end = end;
    }

    /**
     * Metoda zwracająca {@link Account} autora zapotrzebowania.
     * @return konto autora zapotrzebowania.
     */
    public Account getAuthor() {
        return this.author;
    }

    /**
     * Metoda pozwalająca na zmianę autora zapotrzebowania.
     * @param konto nowego autora zapotrzebowania.
     */
    public void setAuthor(Account author) {
        this.author = author;
    }

    /**
     * Metoda zwracająca status zapotrzebowania.
     * @return status zapotrzebowania.
     */
    public Status getStatus() {
        return this.status;
    }

    /**
     * Metoda pozwalająca na zmianę statusu zapotrzebowania.
     * @param nowy status zapotrzebowania.
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Metoda zwracająca jako jaki element część ma zostać wykorzystana.
     * @return napis oznaczający jako jaki element część zapotrzebowania ma zostać wykorzystana.
     */
    public String getElement() {
        return this.element;
    }

    /**
     * Metoda pozwalająca zmienić jako jaki element część ma zostać wykorzystana.
     * @param napis oznaczający nowe przeznaczenie części.
     */
    public void setElement(String element) {
        this.element = element;
    }

    /**
     * Metoda zwracająca część zapotrzebowania.
     * @return część zapotrzebowania.
     */
    public Part getPart() {
        return this.part;
    }

    /**
     * Metoda pozwalająca zmienić część zapotrzebowania.
     * @param nowa część zapotrzebowania.
     */
    public void setPart(Part part) {
        this.part = part;
    }

    /**
     * Metoda pozwalająca zmienić liczbową ilość części.
     * @return ilość części.
     */
    public Double getAmount() {
        return this.amount;
    }

    /**
     * Metoda pozwalająca zmienić ilość części zapotrzebowania.
     * @param nowa ilość części zapotrzebowania.
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * Metoda zwracająca jednostkę ilość części zapotrzebowania.
     * @return jednostka ilości części zapotrzebowania.
     */
    public Unit getUnit() {
        return this.unit;
    }

    /**
     * Metoda pozwalająca na zmianę jednostki ilości części zapotrzebowania.
     * @param nowa jednostka ilości części zapotrzebowania.
     */
    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    /**
     * Metoda zwracająca rysunek techniczny zapotrzebowania.
     * @return rysunek techniczny zapotrzebowania.
     */
    public Drawing getDrawing() {
        return this.drawing;
    }

    /**
     * Metoda pozwalająca na zmianę rysunku technicznego zapotrzebowania.
     * @param nowy rysunek techniczny zapotrzebowania.
     */
    public void setDrawing(Drawing drawing) {
        this.drawing = drawing;
    }

    /**
     * Metoda zwracająca id zapotrzebowania.
     * @return id zapotrzebowania.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Metoda zwracająca uwagi do zapotrzebowania.
     * @return napis będący uwagami do zapotrzebowania.
     */
    public String getComment() {
        return this.comment;
    }

    /**
     * Metoda pozwalająca na zmianę uwag do zapotrzebowania.
     * @param napis będący nowymi uwagami do zapotrzebowania.
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
    
}
