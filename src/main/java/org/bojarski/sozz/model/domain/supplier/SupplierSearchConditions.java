package org.bojarski.sozz.model.domain.supplier;

/**
 * Klasa kryteriów wyszukiwania dostawców.
 * @author Arkadiusz Bojarski
 *
 */
public class SupplierSearchConditions {

    private String name;
    private String email;
    private String web;
    private String phone;
    private String country;
    private String city;
    private String street;
    
    /**
     * Metoda zwracająca kryterium nazwy dostawcy.
     * @return napis będący nazwą dostawcy.
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Metoda pozwalająca na zmianę kryterium nazwy dostawcy.
     * @param napis będący nową nazwą dostawcy.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Metoda zwracająca kryterium adresu email dostawcy.
     * @return napis będący adresem email dostawcy.
     */
    public String getEmail() {
        return this.email;
    }
    
    /**
     * Metoda pozwalająca na zmianę kryterium adresu email dostawcy.
     * @param napis będący nowym adresem email dostawcy.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Metoda zwracająca kryterium strony internetowej dostawcy.
     * @return napis będący adresem strony internetowej dostawcy.
     */
    public String getWeb() {
        return this.web;
    }
    
    /**
     * Metoda pozwalająca na zmianę kryterium adresu strony internetowej dostawcy.
     * @param napis będący nowym adresem strony internetowej dostawcy.
     */
    public void setWeb(String web) {
        this.web = web;
    }
    
    /**
     * Metoda zwracająca kryterium numeru telefonu dostawcy.
     * @return napis będący numerem telefonu dostawcy.
     */
    public String getPhone() {
        return this.phone;
    }
    
    /**
     * Metoda pozwalająca na zmianę kryterium numeru telefonu dostawcy.
     * @param napis będący nowym numerem telefony dostawcy.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    /**
     * Metoda zwracająca kryterium kraju dostawcy.
     * @return napis będący krajem dostawcy.
     */
    public String getCountry() {
        return this.country;
    }
    
    /**
     * Metoda pozwalająca na zmianę kryterium kraju dostawcy.
     * @param napis oznaczający nowy kraj dostawcy.
     */
    public void setCountry(String country) {
        this.country = country;
    }
    
    /**
     * Metoda zwracająca kryterium miasta dostawcy.
     * @return napis oznaczający miasto dostawcy.
     */
    public String getCity() {
        return this.city;
    }
    
    /**
     * Metoda pozwalająca na zmianę kryterium miasta dostawcy.
     * @param napis oznaczający nowe miasto dostawcy.
     */
    public void setCity(String city) {
        this.city = city;
    }
    
    /**
     * Metoda zwracająca kryterium ulicy dostawcy.
     * @return napis oznaczający ulicę dostawcy.
     */
    public String getStreet() {
        return this.street;
    }
    
    /**
     * Metoda pozwalająca na zmianę kryterium ulicy dostawcy.
     * @param napis oznaczający nową uliczę dostawcy.
     */
    public void setStreet(String street) {
        this.street = street;
    }
    
}
