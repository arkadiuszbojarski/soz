package org.bojarski.sozz.service.authorization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bojarski.sozz.model.authentication.AccountAuthentication;
import org.springframework.security.core.Authentication;

/**
 * Interfejs serwisu autoryzacji opartej o token.
 * @author Arkadiusz Bojarski
 *
 */
public interface TokenAuthorizationService {
    
    /**
     * Metoda pozwalająca na dodanie do odpowiedzi token uwierzytelniającego.
     * @param response odpowiedź HTTP.
     * @param authentication
     */
    void addAuthentication(HttpServletResponse response, AccountAuthentication authentication);
    
    /**
     * Metoda pozwalająca na odczytanie token uwierzytelniającego z zapytania.
     * @param request zapytanie HTTP.
     * @return
     */
    Authentication getAuthentication(HttpServletRequest request);
    
}
