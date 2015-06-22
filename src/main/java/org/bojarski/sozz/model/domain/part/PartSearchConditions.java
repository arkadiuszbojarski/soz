package org.bojarski.sozz.model.domain.part;

public class PartSearchConditions {

    private String number;
    private String description;
    private String category;
    private String material;
    private String supplier;
    
    /**
     * @return the number
     */
    public String getNumber() {
        return this.number;
    }
    
    /**
     * @param number the number to set
     */
    public void setNumber(String number) {
        this.number = number;
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
     * @param category the category name to set
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
     * @param supplier the supplier name to set
     */
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
    
}
