package org.bojarski.sozz.model.domain.address;

import javax.persistence.Embeddable;

import com.google.common.base.MoreObjects;
import com.mysema.query.annotations.QueryEmbeddable;

/**
 * Klasa obiektu domeny adresu {@link Supplier}.
 * @author Arkadiusz Bojarski
 *
 */
@QueryEmbeddable
@Embeddable
public class Address {

    private String country;

    private String city;

    private String postal_code;

    private String street;
    
    @SuppressWarnings("unused")
    private Address() {
        
    }

    /**
     * Konstruktor zapamiętujący podane dane adresowe.
     * @param napis oznaczający kraj w adresie.
     * @param napis oznaczający miasto w adresie.
     * @param napis oznaczający kod pocztowy lub podobny w adresie.
     * @param napis oznaczający nazwę ulicy i dokładny adres w adresie.
     */
    public Address(String country, String city, String postal_code,
            String street) {
        this.country = country;
        this.city = city;
        this.postal_code = postal_code;
        this.street = street;
    }

    /**
     * Metoda zwracająca kraj w adresie.
     * @return napis oznaczający kraj w adresie.
     */
    public String getCountry() {
        return this.country;
    }

    /**
     * Metoda pozwalająca na zmianę kraju w adresie.
     * @param napis oznaczający nowy kraj w adresie.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Metoda zwracająca miasto w adresie.
     * @return napis oznaczający miasto w adresie.
     */
    public String getCity() {
        return this.city;
    }

    /**
     * Metoda pozwalająca na zmianę miasta w adresie.
     * @param napis oznaczający nowe miasto w adresie.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Metoda zwracająca adres pocztowy lub podobny w adresie.
     * @return napis oznaczający adres pocztowy lub podobny w adresie.
     */
    public String getPostal_code() {
        return this.postal_code;
    }

    /**
     * Metoda pozwalająca na zmianę adresu pocztowego lub podobnego w adresie.
     * @param napis oznaczający adres pocztowy lub podobny w adresie.
     */
    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    /**
     * Metoda zwracająca nazwę ulicy wraz z dokładnym adresem.
     * @return napis będący nazwą ulicy i dokładnym adresem.
     */
    public String getStreet() {
        return this.street;
    }

    /**
     * Metoda pozwalająca na zmianę nazwy ulicy i dokładnego adresu.
     * @param napis będący nową nazwą ulicy wraz z dokładnym adresem.
     */
    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("country", country.replaceFirst(country.substring(2), "***"))
                .add("city", city.replaceFirst(city.substring(2), "***"))
                .add("postal code", postal_code.replaceFirst(postal_code.substring(2), "***"))
                .add("street", street.replaceFirst(street.substring(2), "***"))
                .toString();
    }
    
}
