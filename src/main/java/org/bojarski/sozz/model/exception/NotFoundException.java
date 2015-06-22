package org.bojarski.sozz.model.exception;

public class NotFoundException extends RuntimeException {
    
    private static final long serialVersionUID = 5868681233356218139L;

    private String code;
    
    private String identity;

    public NotFoundException(String code, String identity, String message) {
        super(message);
        this.code = code;
        this.identity = identity;
    }

    public String getCode() {
        return this.code;
    }

    public String getIdentity() {
        return this.identity;
    }

}
