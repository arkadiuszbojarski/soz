package org.bojarski.sozz.model.domain.contact;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.validation.Valid;

import org.bojarski.sozz.model.domain.address.Address;

import com.google.common.base.MoreObjects;
import com.mysema.query.annotations.QueryEmbeddable;

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

    public Contact(String email, String web_site, String phone, Address address) {
        this.email = email;
        this.web_site = web_site;
        this.phone = phone;
        this.address = address;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeb_site() {
        return this.web_site;
    }

    public void setWeb_site(String web_site) {
        this.web_site = web_site;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("email", email.replaceFirst("@.*", "@***"))
                .add("web site", web_site.replaceFirst(web_site.substring(13), "***"))
                .add("phone", phone.replaceFirst(phone.substring(2), "***"))
                .add("address", address)
                .toString();
    }

}
