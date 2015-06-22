package org.bojarski.sozz.model.domain.address;

import javax.persistence.Embeddable;

import com.google.common.base.MoreObjects;
import com.mysema.query.annotations.QueryEmbeddable;

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

    public Address(String country, String city, String postal_code,
            String street) {
        this.country = country;
        this.city = city;
        this.postal_code = postal_code;
        this.street = street;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostal_code() {
        return this.postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getStreet() {
        return this.street;
    }

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
