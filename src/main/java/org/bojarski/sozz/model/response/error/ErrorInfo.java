package org.bojarski.sozz.model.response.error;

/**
 * Klasa obiektu DTO dla błędów generowanych w aplikacji.
 * @author Arkadiusz Bojarski
 *
 */
public class ErrorInfo {
    
    private String code;
    private String message;
    
    /**
     * Konstruktor przechowujący kod i domyślną treść wiadomości.
     * @param napis będący kodem wiadomości.
     * @param napis stanowiący treść wiadomości.
     */
    public ErrorInfo(String code, String message) {
        this.setCode(code);
        this.message = message;
    }

    /**
     * Metoda zwracająca treść treść wiadomości.
     * @return napis stanowiący treść wiadomości.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Metoda pozwalająca na zmianę treści wiadomości.
     * @param napis będący nową treścią wiadomości.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Metoda zwracająca kod wiadomości.
     * @return napis będący kodem wiadomości.
     */
    public String getCode() {
        return code;
    }

    /**
     * Metoda pozwalająca na zmianę kodu wiadomości.
     * @param napis będący nowym kodem wiadomości.
     */
    public void setCode(String code) {
        this.code = code;
    }
    
}
