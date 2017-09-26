package org.bojarski.sozz.model.domain.account;

/**
 * Interfejs klas DTO zawierających hasło i powtórzone hasło użytkownika. 
 * @author Arkadiusz Bojarski
 *
 */
public interface AccountPasswordForm {
    
    /**
     * Szkielet metody zwracającej hasło użytkownika.
     * @return napis będący hasłem użytkownika.
     */
    String getPassword();
    
    /**
     * Szkielet metody zwracającej powtórzone hasło użytkownika.
     * @return napis będący powtórzonym hasłem użytkownika.
     */
    String getPasswordRepeated();
    
}
