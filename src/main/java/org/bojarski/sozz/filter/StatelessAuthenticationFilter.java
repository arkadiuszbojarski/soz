package org.bojarski.sozz.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.bojarski.sozz.model.authentication.AuthenticationToken;
import org.bojarski.sozz.service.authorization.TokenAuthorizationService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

/**
 * Klasa bezstanowego filtru uwierzytelniania.
 * @author Arkadiusz Bojarski
 *
 */
public class StatelessAuthenticationFilter extends GenericFilterBean {
    
    private final TokenAuthorizationService tokenAuthorizationService;
    
    /**
     * Konstruktor zapamiętujący referencję do {@link TokenAuthorizationService}.
     * @param tokenAuthorizationService serwis obsługujący uwierzytelnianie za pomocą {@link AuthenticationToken}.
     */
    public StatelessAuthenticationFilter(TokenAuthorizationService tokenAuthorizationService) {
        this.tokenAuthorizationService = tokenAuthorizationService;
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(
                tokenAuthorizationService.getAuthentication((HttpServletRequest) request));
        chain.doFilter(request, response);
    }

}
