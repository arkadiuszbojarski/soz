package org.bojarski.sozz.dto;

/**
 * Interfejs klas DTO określających tożsamość użytkownika za pomocą nazwy użytkownika i jego adresu email.
 * @author Arkadiusz Bojarski
 *
 */
public interface AccountIdentityForm {

    /**
     * Szkielet metody zwracającej nazwę użytkownika.
     * @return napis będący nazwą użytkownika.
     */
    String getUsername();
    
    /**
     * Szkielet metody zwracającej adres email użytkownika.
     * @return napis będący adresem email użytkownika.
     */
    String getEmail();
    
}
