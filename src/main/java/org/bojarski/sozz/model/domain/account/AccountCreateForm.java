package org.bojarski.sozz.model.domain.account;

import javax.validation.constraints.Size;

import org.bojarski.sozz.model.domain.validation.Validation.Extended;
import org.bojarski.sozz.model.domain.validation.account.AccountPasswordMatch;
import org.bojarski.sozz.model.domain.validation.account.UniqueAccountEmail;
import org.bojarski.sozz.model.domain.validation.account.UniqueAccountName;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Klasa obiektu DTO formularza tworzenia nowego użytkownika.
 * @author Arkadiusz Bojarski
 *
 */
@UniqueAccountEmail(groups = Extended.class)
@UniqueAccountName(groups = Extended.class)
@AccountPasswordMatch
public class AccountCreateForm implements AccountPasswordForm, AccountIdentityForm {

    @NotBlank
    private String username = "";
    
    @NotBlank
    @Email
    private String email = "";

    @NotEmpty
    @Size(min = 8)
    private String password = "";

    @NotEmpty
    private String passwordRepeated = "";
    
    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    /**
     * Metoda pozwalająca na zmianę adresu email w formularzu dodawania nowego użytkownika.
     * @param email napis będący nowym adresem email w formularzu dodawania nowego użytkownika.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * Metoda pozwalająca na zmianę hasła w formularzu dodawania nowego użytkownika.
     * @param password napis będący nowym hasłem w formularzu dodawania nowego użytkownika. 
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPasswordRepeated() {
        return this.passwordRepeated;
    }

    /**
     * Metoda pozwalająca na zmianę powtórzonego hasła w formularzu dodawania nowego użytkownika.
     * @param passwordRepeated napis będący nowym powtórzonym hasłem w formularzu dodawania nowego użytkownika.
     */
    public void setPasswordRepeated(String passwordRepeated) {
        this.passwordRepeated = passwordRepeated;
    }

}
