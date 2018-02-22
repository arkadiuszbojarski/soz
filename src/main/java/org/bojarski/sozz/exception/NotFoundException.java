package org.bojarski.sozz.exception;

/**
 * Klasa wyjątku nieodnalezienia żądanego zasobu. 
 * @author Arkadiusz Bojarski
 *
 */
public class NotFoundException extends RuntimeException {
    
    private static final long serialVersionUID = 5868681233356218139L;

    private String code;
    
    private String identity;

    /**
     * Konstruktor przechowujący kod wiadomości, identyfikator zasobu
     * oraz domyślną treść wiadomości.
     * @param napis będący kodem wiadomości.
     * @param napis będący identyfikatorem zasobu.
     * @param napis będący domyślną treścią wiadomości.
     */
    public NotFoundException(String code, String identity, String message) {
        super(message);
        this.code = code;
        this.identity = identity;
    }

    /**
     * Metoda zwracająca kod wiadomości.
     * @return napis będący kodem wiadomości.
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Metoda zwracająca identyfikator zasobu.
     * @return napis będący identyfikatorem zasobu.
     */
    public String getIdentity() {
        return this.identity;
    }

}
