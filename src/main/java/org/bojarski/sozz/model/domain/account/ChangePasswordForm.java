package org.bojarski.sozz.model.domain.account;

import javax.validation.constraints.Size;

import org.bojarski.sozz.model.domain.validation.account.AccountPasswordMatch;
import org.hibernate.validator.constraints.NotBlank;

@AccountPasswordMatch
public class ChangePasswordForm implements AccountPasswordForm {

    @NotBlank
    @Size(min = 8)
    private String password;
    
    @NotBlank
    private String passwordRepeated;
    
    @NotBlank
    private String passwordOld;

    /**
     * @return the password
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the passwordRepeated
     */
    @Override
    public String getPasswordRepeated() {
        return this.passwordRepeated;
    }

    /**
     * @param passwordRepeated the passwordRepeated to set
     */
    public void setPasswordRepeated(String passwordRepeated) {
        this.passwordRepeated = passwordRepeated;
    }

    /**
     * @return the passwordOld
     */
    public String getPasswordOld() {
        return this.passwordOld;
    }

    /**
     * @param passwordOld the passwordOld to set
     */
    public void setPasswordOld(String passwordOld) {
        this.passwordOld = passwordOld;
    }
    
}
