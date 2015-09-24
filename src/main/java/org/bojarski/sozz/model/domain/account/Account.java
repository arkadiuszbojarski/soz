package org.bojarski.sozz.model.domain.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.bojarski.sozz.model.views.View.BasicView;
import org.bojarski.sozz.model.views.View.ExtendedView;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.base.MoreObjects;
import com.mysema.query.annotations.QueryEntity;

/**
 * Klasa obiektu domeny: konto użytkownika.
 * @author Arkadiusz Bojarski
 *
 */
@QueryEntity
@Entity
@Table(name = "account")
public class Account {
    
    @JsonView(BasicView.class)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    
    @JsonView(BasicView.class)
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    
    @JsonView(BasicView.class)
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;
    
    @JsonView(ExtendedView.class)
    @Column(name = "enabled", nullable = false)
    private boolean enabled = false;
    
    @JsonView(ExtendedView.class)
    @Column(name = "locked", nullable = false)
    private boolean locked = false;
    
    @JsonView(BasicView.class)
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;
    
    /**
     * Metoda zwracająca nazwę użytkownika.
     * @return napis będą nazwą użytkownika
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Metoda pozwalająca zmienić nazwę użytkownika.
     * @param username napis będący nową nazwą użytkownika.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Metoda określająca czy konto użytkownika jest aktywowane.
     * @return wartość logiczna określająca czy konto użytkownika jest aktywowane.
     */
    public boolean isEnabled() {
        return this.enabled;
    }

    /**
     * Metoda pozwalająca zmienić to czy konto użytkownika jest aktywowane.
     * @param enabled wartość logiczna określająca nowy stan aktywowania konta.
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Metoda określająca czy konta użytkownika jest zablokowane.
     * @return wartość logiczna określająca czy konta użytkownika jest zablokowane.
     */
    public boolean isLocked() {
        return this.locked;
    }

    /**
     * Metoda pozwalająca zmienić to czy konta użytkownika jest zablokowane.
     * @param locked wartość logiczna określająca nowy stan zablokowania konta.
     */
    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    /**
     * Metoda zwracająca adres email użytkownika.
     * @return napis będący adresem email użytkownika.
     */
    public String getEmail() {
        return this.email;
    }

    
    /**
     * Metoda pozwalająca zmienić adres email użytkownika.
     * @param email napis będący nowym adresem email użytkownika.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Metoda zwracająca hash hasła użytkownika.
     * @return napis będący hash'em hasła użytkownika.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Metoda pozwalająca zmienić hash hasła użytkownika.
     * @param password napis będący nowym hash'em hasła użytkownika.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Metoda zwracająca numer id użytkownika.
     * @return numer id użytkownika.
     */
    public Long getId() {
        return this.id;
    }
    
    /**
     * Metoda zwracająca {@link Role} użytkownika.
     * @return rola użytkownika.
     */
    public Role getRole() {
        return this.role;
    }

    /**
     * Metoda pozwalająca zmienić rolę użytkownika.
     * @param role nowa rola użytkownika.
     */
    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("username", username)
                .add("email", email.replaceFirst("@.*", "***"))
                .add("password", "...")
                .toString();
    }

}


