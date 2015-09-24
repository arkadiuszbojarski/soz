package org.bojarski.sozz.model.exception;

/**
 * Klasa wyjątku rzucanego w przypadku podania nieprawidłowego hasła
 * w formularzu.
 * @author Arkadiusz Bojarski
 *
 */
public class WrongPasswordException extends RuntimeException {

    private static final long serialVersionUID = -7822459212433231233L;

    private String code;
    
    /**
     * Konstruktor przechowujący kod wiadomości oraz domyślną treść wiadomości.
     * @param code
     * @param message
     */
    public WrongPasswordException(String code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * Metoda zwracająca kod wiadomości.
     * @return
     */
    public String getCode() {
        return this.code;
    }

}
