package org.bojarski.sozz.dto;

/**
 * Klasa obiektu DTO formularza logowania użytkownika.
 * @author Arkadiusz Bojarski
 *
 */
public class LoginForm {

    private String username;
    
    private String password;

    @SuppressWarnings("unused")
    private LoginForm() {
        
    }

    /**
     * Konstruktor zapamiętujący nazwę i hasło użytkownika.
     * @param napis będący nazwą użytkownika.
     * @param napis będący hasłem użytkownika.
     */
    public LoginForm(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Metoda zwracająca nazwę użytkownika.
     * @return napis będący nazwą użytkownika.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Metoda zwracająca hasło użytkownika.
     * @return napis będący hasłem użytkownika.
     */
    public String getPassword() {
        return this.password;
    }

}
