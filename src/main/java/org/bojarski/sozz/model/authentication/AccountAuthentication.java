package org.bojarski.sozz.model.authentication;

import java.util.Collection;

import org.bojarski.sozz.model.domain.account.Account;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 * Klasa uwierzytelniania {@link Account}.
 * @author Arkadiusz Bojarski
 *
 */
public class AccountAuthentication implements Authentication {

    private static final long serialVersionUID = 2261423793281313992L;
    
    private final Account account;
    private boolean authenticated = true;
    
    public AccountAuthentication(Account account) {
        this.account = account;
    }
    
    @Override
    public String getName() {
        return account.getUsername();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(account.getRole().toString());
    }

    @Override
    public Object getCredentials() {
        return account.getPassword();
    }

    @Override
    public Object getDetails() {
        return account;
    }

    @Override
    public Object getPrincipal() {
        return account.getUsername();
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated)
            throws IllegalArgumentException {
        if(isAuthenticated == true) {
            throw new IllegalArgumentException();
        }
        this.authenticated = isAuthenticated;
    }

    
}
