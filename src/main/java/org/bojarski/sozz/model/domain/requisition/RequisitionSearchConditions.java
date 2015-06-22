package org.bojarski.sozz.model.domain.requisition;

import java.util.Date;

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
     * @return the number
     */
    public Long getNumber() {
        return this.number;
    }
    
    /**
     * @param number the number to set
     */
    public void setNumber(Long number) {
        this.number = number;
    }
    
    /**
     * @return the after
     */
    public Date getAfter() {
        return this.after;
    }
    
    /**
     * @param after the after to set
     */
    public void setAfter(Date after) {
        this.after = after;
    }
    
    /**
     * @return the before
     */
    public Date getBefore() {
        return this.before;
    }
    
    /**
     * @param before the before to set
     */
    public void setBefore(Date before) {
        this.before = before;
    }
    
    /**
     * @return the author
     */
    public String getAuthor() {
        return this.author;
    }
    
    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }
    
    /**
     * @return the status
     */
    public Status getStatus() {
        return this.status;
    }
    
    /**
     * @param status the status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }
    
    /**
     * @return the element
     */
    public String getElement() {
        return this.element;
    }
    
    /**
     * @param element the element to set
     */
    public void setElement(String element) {
        this.element = element;
    }
    
    /**
     * @return the drawing
     */
    public String getDrawing() {
        return this.drawing;
    }
    
    /**
     * @param drawing the drawing to set
     */
    public void setDrawing(String drawing) {
        this.drawing = drawing;
    }
    
    /**
     * @return the part
     */
    public String getPart() {
        return this.part;
    }
    
    /**
     * @param part the part to set
     */
    public void setPart(String part) {
        this.part = part;
    }
    
    /**
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }
    
    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * @return the category
     */
    public String getCategory() {
        return this.category;
    }
    
    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }
    
    /**
     * @return the material
     */
    public String getMaterial() {
        return this.material;
    }
    
    /**
     * @param material the material to set
     */
    public void setMaterial(String material) {
        this.material = material;
    }
    
    /**
     * @return the supplier
     */
    public String getSupplier() {
        return this.supplier;
    }
    
    /**
     * @param supplier the supplier to set
     */
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

}
