package org.bojarski.sozz.model.response.error;

/**
 * Klasa obiektu DTO dla błędów wynikających z niespełnienia przez pola
 * formularzy nadanych kryteriów.
 * @author Arkadiusz Bojarski
 *
 */
public class FieldErrorInfo {

    private final String field;
    
    private final String code;
    
    private final String message;

    /**
     * Konstruktor przechowujący nazwę pola, kod i treść wiadomości.
     * @param napis będący nazwą pola.
     * @param napis będący kodem wiadomości.
     * @param napis będący treścią wiadomości.
     */
    public FieldErrorInfo(String field, String code, String message) {
        this.field = field;
        this.code = code;
        this.message = message;
    }

    /**
     * Metoda zwracająca nazwę pola.
     * @return napis będący nazwą pola.
     */
    public String getField() {
        return this.field;
    }
    
    /**
     * Metoda zwracająca kod wiadomości.
     * @return napis będący kodem wiadomości.
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Metoda zwracająca treść wiadomości.
     * @return napis oznaczający treść wiadomości.
     */
    public String getMessage() {
        return this.message;
    }
    
}
