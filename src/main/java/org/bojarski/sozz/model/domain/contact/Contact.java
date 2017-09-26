package org.bojarski.sozz.model.domain.contact;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.validation.Valid;

import org.bojarski.sozz.model.domain.address.Address;

import com.querydsl.core.annotations.QueryEmbeddable;

/**
 * Klasa dziedziny modelująca kontakt {@link Supplier}.
 * @author Arkadiusz Bojarski
 *
 */
@QueryEmbeddable
@Embeddable
public class Contact {

    private String email;

    private String web_site;

    private String phone;

    @Valid
    @Embedded
    private Address address;
    
    @SuppressWarnings("unused")
    private Contact() {
        
    }

    /**
     * Konstruktor przechowujący dane kontaktowe dostawcy.
     * @param napis będący adresem email.
     * @param napis będący stroną internetową.
     * @param napis będący numerem telefornu.
     * @param {@link Address} dostawcy.
     */
    public Contact(String email, String web_site, String phone, Address address) {
        this.email = email;
        this.web_site = web_site;
        this.phone = phone;
        this.address = address;
    }

    /**
     * Metoda zwracająca adres email dostawcy.
     * @return napis będący adresem email dostawcy.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Metoda pozwalająca na zmianę adresu email dostawcy.
     * @param napis będący nowym adresem email dostawcy.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Metoda zwracająca stronę internetową dostawcy.
     * @return napis będący stroną internetową dostawcy.
     */
    public String getWeb_site() {
        return this.web_site;
    }

    /**
     * Metoda pozwalająca na zmianę strony internetowej dostawcy.
     * @param napis będący nową stroną internetową dostawcy.
     */
    public void setWeb_site(String web_site) {
        this.web_site = web_site;
    }

    /**
     * Metoda zwracająca numer telefonu dostawcy.
     * @return napis będący numerem telefonu dostawcy.
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * Metoda pozwalająca na zmianę numeru telefonu dostawcy.
     * @param napis będący nowym numerem telefonu dostawcy.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    /**
     * Metoda zwracająca adres dostawcy.
     * @return adres dostawcy.
     */
    public Address getAddress() {
        return this.address;
    }

    /**
     * Metoda pozwalająca na zmianę adresu dostawcy.
     * @param nowy adres dostawcy.
     */
    public void setAddress(Address address) {
        this.address = address;
    }
}
