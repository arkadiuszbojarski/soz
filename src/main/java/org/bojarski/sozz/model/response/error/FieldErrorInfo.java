package org.bojarski.sozz.model.response.error;

public class FieldErrorInfo {

    private final String field;
    
    private final String code;
    
    private final String message;

    public FieldErrorInfo(String field, String code, String message) {
        this.field = field;
        this.code = code;
        this.message = message;
    }

    public String getField() {
        return this.field;
    }
    
    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
    
}
