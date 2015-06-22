package org.bojarski.sozz.model.exception;

public class AlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = -2705345139031062051L;

    private String code;
    
    private String field;

    public AlreadyExistsException(String code, String field, String message) {
        super(message);
        this.code = code;
        this.field = field;
    }

    public String getCode() {
        return this.code;
    }

    public String getField() {
        return this.field;
    }
    
}
