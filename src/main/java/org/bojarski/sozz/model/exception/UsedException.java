package org.bojarski.sozz.model.exception;

/**
 * Klasa wyjątku rzucanego w momencie próby usunięcia
 * zasobu wykorzystywanego w innym miejscu.
 * @author Arkadiusz Bojarski
 *
 */
public class UsedException extends RuntimeException {
    
    private static final long serialVersionUID = -7356199897100979325L;

    private String code;
    
    private Long id;

    /**
     * Konstruktor przechowujący kod wiadomości oraz id zasobu.
     * @param napis będący kodem wiadomości.
     * @param id zasobu.
     */
    public UsedException(String code, Long id) {
        this.code = code;
        this.id = id;
    }

    /**
     * Metoda zwracająca kod wiadomości.
     * @return napis będący kodem wiadomości.
     */
    public String getCode() {
        return this.code;
    }
    
    /**
     * Metoda zwracająca id zasobu.
     * @return id zasobu.
     */
    public Long getId() {
        return this.id;
    }
    
}
