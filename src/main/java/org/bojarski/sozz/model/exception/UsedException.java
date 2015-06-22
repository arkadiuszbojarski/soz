package org.bojarski.sozz.model.exception;

public class UsedException extends RuntimeException {
    
    private static final long serialVersionUID = -7356199897100979325L;

    private String code;
    
    private Long id;

    public UsedException(String code, Long id) {
        this.code = code;
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }
    
    public Long getId() {
        return this.id;
    }
    
}
