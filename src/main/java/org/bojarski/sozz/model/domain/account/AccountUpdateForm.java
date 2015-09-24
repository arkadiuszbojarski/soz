package org.bojarski.sozz.model.domain.account;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Klasa obiektu DTO formularza edycji konta użytkownika.
 * @author Arkadiusz Bojarski
 *
 */
public class AccountUpdateForm implements AccountIdentityForm {

    @NotBlank
    private String username;
    
    @NotBlank
    @Email
    private String email;
    
    @NotNull
    private Boolean enabled = false;
    
    @NotNull
    private Boolean locked = false;
    
    @NotNull
    private Role role = Role.USER;
    
    /**
     * Metoda pozwalająca na zmianę nazwy użytkownika.
     * @param napis będący nową nazwą użytkownika.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
    
    /**
     * Metoda pozwalająca na zmianę adresu email użytkownika.
     * @param napis będący nowym adresem email użytkownika.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String getEmail() {
        return this.email;
    }

    /**
     * Metoda zwracająca stan aktywowania konta użytkownika.
     * @return wartość logiczna określająca stan aktywowania konta użytkownika.
     */
    public Boolean getEnabled() {
        return this.enabled;
    }

    /**
     * Metoda pozwalająca na zmianę stanu aktywowania konta użytkownika.
     * @param wartość logiczna będąca nowym stanem aktywowania konta użytkownika.
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Metoda zwracająca stan zablokowania konta użytkownika.
     * @return wartość logiczna określająca stan zablokowania konta użytkownika.
     */
    public Boolean getLocked() {
        return this.locked;
    }

    /**
     * Metoda pozwalająca na zmianę stanu zablokowania konta użytkownika.
     * @param wartość logiczna będąca nowym stanem zablokowania konta użytkownika.
     */
    public void setLocked(Boolean locked) {
        this.locked = locked;
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
     * @param nowa rola użytkownika.
     */
    public void setRole(Role role) {
        this.role = role;
    }
    
}
