package org.bojarski.sozz.model.exception;

/**
 * Klasa wyjątku niespełnienia kryterium unikalności.
 * @author Arkadiusz Bojarski
 *
 */
public class AlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = -2705345139031062051L;

    private String code;
    
    private String field;

    /**
     * Konstruktor przechowujący kod wiadomości, nazwę pola oraz domyślną treść wiadomości.
     * @param napis oznaczający kod wiadomości.
     * @param napis oznaczający nazwę pola.
     * @param napis oznaczający domyślną treść wiadomości.
     */
    public AlreadyExistsException(String code, String field, String message) {
        super(message);
        this.code = code;
        this.field = field;
    }

    /**
     * Metoda zwracająca kod wiadomości.
     * @return napis będący kodem wiadomości.
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Metoda zwracająca nazwę pola.
     * @return napis będący nazwą pola.
     */
    public String getField() {
        return this.field;
    }
    
}
