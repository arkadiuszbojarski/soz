package org.bojarski.sozz.model.domain.account;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class AccountUpdateForm implements AccountIdentityForm {

    @NotBlank
    private String username;
    
    @NotBlank
    @Email
    private String email;
    
    @NotNull
    private Boolean enabled = false;
    
    @NotNull
    private Boolean locked = false;
    
    //@EnumValue(enumClass = Role.class)
    @NotNull
    private Role role = Role.USER;
    
    /**
     * @param username the user name to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the user name
     */
    @Override
    public String getUsername() {
        return this.username;
    }
    
    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * @return the email
     */
    @Override
    public String getEmail() {
        return this.email;
    }

    /**
     * @return the enabled
     */
    public Boolean getEnabled() {
        return this.enabled;
    }

    /**
     * @param enabled the enabled to set
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the locked
     */
    public Boolean getLocked() {
        return this.locked;
    }

    /**
     * @param locked the locked to set
     */
    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    /**
     * @return the role
     */
    public Role getRole() {
        return this.role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(Role role) {
        this.role = role;
    }
    
}
