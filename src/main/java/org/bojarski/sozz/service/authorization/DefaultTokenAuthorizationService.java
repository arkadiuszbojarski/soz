package org.bojarski.sozz.service.authorization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bojarski.sozz.model.authentication.AccountAuthentication;
import org.bojarski.sozz.model.domain.account.Account;
import org.bojarski.sozz.repository.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * Implementacja interfejs serwisu autoryzacji opartej o token.
 * @author Arkadiusz Bojarski
 *
 */
@Component
public class DefaultTokenAuthorizationService implements
        TokenAuthorizationService {

    private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
    
    private final TokenHandler tokenHandler;
    private final AccountRepository accountRepository;
    
    @Autowired
    public DefaultTokenAuthorizationService(TokenHandler tokenHandler, AccountRepository accountRepository) {
        this.tokenHandler = tokenHandler;
        this.accountRepository = accountRepository;
    }
    
    @Override
    public void addAuthentication(HttpServletResponse response,
            AccountAuthentication authentication) {
        final Account account = (Account) authentication.getDetails();
        response.addHeader(AUTH_HEADER_NAME, tokenHandler.createAuthenticationTokenForAccount(account));
    }

    @Override
    public Authentication getAuthentication(HttpServletRequest request) {
        final String token = request.getHeader(AUTH_HEADER_NAME);
        if(token != null) {
            final Account parsed = tokenHandler.parseAccountFromAuthenticationToken(token);
            if(parsed != null) {
                final Account retreived = accountRepository.findOne(parsed.getId());
                if(retreived != null && retreived.isEnabled() && !retreived.isLocked()) {
                    return new AccountAuthentication(retreived);
                }
            }
        }
        return null;
    }

}
