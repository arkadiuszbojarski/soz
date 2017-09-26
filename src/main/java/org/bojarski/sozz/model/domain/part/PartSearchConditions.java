package org.bojarski.sozz.model.domain.part;

/**
 * Klasa kryteriów wyszukiwania części.
 * @author Arkadiusz Bojarski
 *
 */
public class PartSearchConditions {

    private String number;
    private String description;
    private String category;
    private String material;
    private String supplier;
    
    /**
     * Metoda zwracająca numer katalogowy części w kryteriach wyszukiwania części.
     * @return napis będący numerem katalogowym części w kryteriach wyszukiwania części.
     */
    public String getNumber() {
        return this.number;
    }
    
    /**
     * Metoda pozwalająca na zmianę numeru katalogowego części w kryteriach wyszukiwania części.
     * @param napis będący nowym numerem katalogowym części w kryteriach wyszukiwania części.
     */
    public void setNumber(String number) {
        this.number = number;
    }
    
    /**
     * Metoda wracająca opis części w kryteriach wyszukiwania części.
     * @return napis będący opisem części w kryteriach wyszukiwania części.
     */
    public String getDescription() {
        return this.description;
    }
    
    /**
     * Metoda pozwalająca na zmianę opisu części w kryteriach wyszukiwania części.
     * @param napis będący nowym opisem części w kryteriach wyszukiwania części.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Metoda zwracająca nazwę kategorię części w kryteriach wyszukiwania części.
     * @return napis będący nazwą kategorii części w kryteriach wyszukiwania części.
     */
    public String getCategory() {
        return this.category;
    }
    
    /**
     * Metoda pozwalająca na zmianę nazwy kategorii części w kryteriach wyszukiwania części.
     * @param napis będący nową nazwą kategorii części w kryteriach wyszukiwania części.
     */
    public void setCategory(String category) {
        this.category = category;
    }
    
    /**
     * Metoda zwracająca materiał części w kryteriach wyszukiwania części.
     * @return napis będący materiałem części w kryteriach wyszukiwania części.
     */
    public String getMaterial() {
        return this.material;
    }
    
    /**
     * Metoda pozwalająca na zmianę materiału części w kryteriach wyszukiwania części.
     * @param napis będący nowym materiałem części w kryteriach wyszukiwania części.
     */
    public void setMaterial(String material) {
        this.material = material;
    }
    
    /**
     * Metoda zwracająca nazwę dostawcy części w kryteriach wyszukiwania części.
     * @return napis będący nazwą dostawcy części w kryteriach wyszukiwania części.
     */
    public String getSupplier() {
        return this.supplier;
    }
    
    /**
     * Metoda pozwalająca na zmianę nazwy dostawcy części w kryteriach wyszukiwania części.
     * @param napis będący nową nazwą dostawcy części w kryteriach wyszukiwania części.
     */
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
    
}
