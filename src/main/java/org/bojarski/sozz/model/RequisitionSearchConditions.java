package org.bojarski.sozz.model;

import java.util.Date;

/**
 * Klasa kryteriów wyszukiwania zapotrzebowań.
 * @author Arkadiusz Bojarski
 *
 */
public class RequisitionSearchConditions {

    private Long number;
    private Date after;
    private Date before;
    private String author;
    private Status status;
    private String element;
    private String drawing;
    private String part;
    private String description;
    private String category;
    private String material;
    private String supplier;
    
    /**
     * Metoda zwracająca kryterium numeru zapotrzebowania.
     * @return numer zapotrzebowania.
     */
    public Long getNumber() {
        return this.number;
    }
    
    /**
     * Metoda pozwalająca zmienić kryterium numeru zapotrzebowania.
     * @param nowy numer zapotrzebowania.
     */
    public void setNumber(Long number) {
        this.number = number;
    }
    
    /**
     * Metoda zwracająca kryterium utworzenia zapotrzebowania po dacie.
     * @return data po jakiej utworzono zapotrzebowanie.
     */
    public Date getAfter() {
        return this.after;
    }
    
    /**
     * Metoda pozwalająca zmienić kryterium utworzenia zapotrzebowania po dacie.
     * @param nowa data po jakiej utworzono zapotrzebowanie.
     */
    public void setAfter(Date after) {
        this.after = after;
    }
    
    /**
     * Metoda zwracająca kryterium utworzenia zapotrzebowania przed datą.
     * @return data przed jaką utworzono zapotrzebowanie.
     */
    public Date getBefore() {
        return this.before;
    }
    
    /**
     * Metoda pozwalająca zmienić kryterium utworzenia zapotrzebowania przed datą.
     * @param nowa data przed jaką utworzono zapotrzebowanie.
     */
    public void setBefore(Date before) {
        this.before = before;
    }
    
    /**
     * Metoda zwracająca kryterium nazwy autora zapotrzebowania.
     * @return napis będący nazwą autora zapotrzebowania.
     */
    public String getAuthor() {
        return this.author;
    }
    
    /**
     * Metoda pozwalająca na zmianę kryterium nazwy autora zapotrzebowania.
     * @param napis będący nową nazwą autora zapotrzebowania.
     */
    public void setAuthor(String author) {
        this.author = author;
    }
    
    /**
     * Metoda zwracająca kryterium statusu zapotrzebowania.
     * @return status zapotrzebowania.
     */
    public Status getStatus() {
        return this.status;
    }
    
    /**
     * Metoda pozwalająca na zmianę kryterium statusu zapotrzebowania.
     * @param nowy status zapotrzebowania.
     */
    public void setStatus(Status status) {
        this.status = status;
    }
    
    /**
     * Metoda zwracająca kryterium przeznaczenia części zapotrzebowania.
     * @return napis określający przeznaczenie części zapotrzebowania.
     */
    public String getElement() {
        return this.element;
    }
    
    /**
     * Metoda pozwalająca na zmianę kryterium przeznaczenia części zapotrzebowania.
     * @param napis będący nowym przeznaczeniem części zapotrzebowania.
     */
    public void setElement(String element) {
        this.element = element;
    }
    
    /**
     * Metoda zwracająca kryterium numeru rysunku technicznego zapotrzebowania.
     * @return napis będący numerem rysunku technicznego zapotrzebowania.
     */
    public String getDrawing() {
        return this.drawing;
    }
    
    /**
     * Metoda pozwalająca na zmianę kryterium numeru rysunku technicznego zapotrzebowania.
     * @param napis będący nowym numerem rysunku technicznego zapotrzebowania.
     */
    public void setDrawing(String drawing) {
        this.drawing = drawing;
    }
    
    /**
     * Metoda zwracająca kryterium numeru katalogowego części zapotrzebowania.
     * @return napis będący numerem katalogowym części zapotrzebowania.
     */
    public String getPart() {
        return this.part;
    }
    
    /**
     * Metoda pozwalająca na zmianę kryterium numeru katalogowego części zapotrzebowania.
     * @param napis będący nowym numerem katalogowym części zapotrzebowania.
     */
    public void setPart(String part) {
        this.part = part;
    }
    
    /**
     * Metoda zwracająca kryterium opisu części zapotrzebowania.
     * @return napis będący opisem części zapotrzebowania.
     */
    public String getDescription() {
        return this.description;
    }
    
    /**
     * Metoda pozwalająca na zmianę kryterium opisu części zapotrzebowania.
     * @param napis będący nowym opisem części zapotrzebowania.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Metoda zwracająca kryterium nazwy kategorii części zapotrzebowania.
     * @return napis będący nazwą kategorii części zapotrzebowania.
     */
    public String getCategory() {
        return this.category;
    }
    
    /**
     * Metoda pozwalająca na zmianę kryterium nazwy kategorii części zapotrzebowania.
     * @param napis będący nowym kryterium nazwy kategorii części zapotrzebowania.
     */
    public void setCategory(String category) {
        this.category = category;
    }
    
    /**
     * Metoda zwracająca kryterium materiału części zapotrzebowania.
     * @return napis określający materiał części zapotrzebowania.
     */
    public String getMaterial() {
        return this.material;
    }
    
    /**
     * Metoda pozwalająca na zmianę kryterium materiału części zapotrzebowania.
     * @param napis oznaczający nowy materiał części zapotrzebowania.
     */
    public void setMaterial(String material) {
        this.material = material;
    }
    
    /**
     * Metoda zwracająca kryterium nazwy dostawcy części zapotrzebowania.
     * @return napis będący nazwą dostawcy części zapotrzebowania.
     */
    public String getSupplier() {
        return this.supplier;
    }
    
    /**
     * Metoda pozwalająca na zmianę kryterium nazwy dostawcy części zapotrzebowania.
     * @param napis będący nową nazwą dostawcy części zapotrzebowania.
     */
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

}
