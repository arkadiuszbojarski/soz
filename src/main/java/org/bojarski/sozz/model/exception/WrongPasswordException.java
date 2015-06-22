package org.bojarski.sozz.model.exception;


public class WrongPasswordException extends RuntimeException {

    private static final long serialVersionUID = -7822459212433231233L;

    private String code;
    
    public WrongPasswordException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

}
