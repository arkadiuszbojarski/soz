package org.bojarski.sozz.model.domain.account;

import javax.validation.constraints.Size;

import org.bojarski.sozz.model.domain.validation.account.AccountPasswordMatch;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Klasa obiektu DTO formularza zmieny hasła konta użytkownika.
 * @author Arkadiusz Bojarski
 *
 */
@AccountPasswordMatch
public class ChangePasswordForm implements AccountPasswordForm {

    @NotBlank
    @Size(min = 8)
    private String password;
    
    @NotBlank
    private String passwordRepeated;
    
    @NotBlank
    private String passwordOld;

    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * Metoda pozwalająca na zmianę hasła formularza zmiany hasła konta użytkownika.
     * @param napis będący nowym hasłem formularza zmiany hasła konta użytkownika.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPasswordRepeated() {
        return this.passwordRepeated;
    }

    /**
     * Metoda pozwalająca na zmianę powtórzonego hasła formularza zmiany hasła konta użytkownika.
     * @param napis będący nowym powtórzonym hasłem formularza zmiany hasła konta użytkownika.
     */
    public void setPasswordRepeated(String passwordRepeated) {
        this.passwordRepeated = passwordRepeated;
    }

    /**
     * Metoda zwracająca stare hasło formularza zmiany hasła konta użytkownika.
     * @return napis będący starym hasłem użytkownika.
     */
    public String getPasswordOld() {
        return this.passwordOld;
    }

    /**
     * Metoda pozwalająca zmienić stare hasło formularza zmiany hasła konta użytkownika.
     * @param napis będący nowym starym hasłem formularza zmiany hasła konta użytkownika.
     */
    public void setPasswordOld(String passwordOld) {
        this.passwordOld = passwordOld;
    }
    
}
