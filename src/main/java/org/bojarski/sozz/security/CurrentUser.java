package org.bojarski.sozz.security;

import java.util.Collection;

import org.bojarski.sozz.model.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Klasa przechowująca informacje o aktualnym użytkowniku
 * implementująca interfejs {@link UserDetails}.
 * @author Arkadiusz Bojarski
 *
 */
public class CurrentUser implements UserDetails {

    private static final long serialVersionUID = -6568109777150628616L;

    private Account account;
    
    /**
     * Konstruktor zapamiętujący {@link Account} dla którego jest tworzony
     * obiekt klasy.
     * @param account
     */
    public CurrentUser(Account account) {
        this.account = account;
    }
    
    /**
     * Metoda zwracająca konto użytkownika.
     * @return konto użytkownika dla którego utworzono obiektu klasy.
     */
    public Account getAccount() {
        return this.account;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(account.getRole().toString());
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !account.isLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return account.isEnabled();
    }
    
}
