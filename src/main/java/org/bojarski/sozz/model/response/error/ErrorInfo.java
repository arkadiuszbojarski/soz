package org.bojarski.sozz.model.response.error;

public class ErrorInfo {
    
    private String code;
    private String message;
    
    public ErrorInfo(String code, String message) {
        this.setCode(code);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
}
