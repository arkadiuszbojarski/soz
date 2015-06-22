package org.bojarski.sozz.model.domain.account;

public class LoginForm {

    private String username;
    
    private String password;

    @SuppressWarnings("unused")
    private LoginForm() {
        
    }

    /**
     * @param username
     * @param password
     */
    public LoginForm(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * @return the user name
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return this.password;
    }

}
