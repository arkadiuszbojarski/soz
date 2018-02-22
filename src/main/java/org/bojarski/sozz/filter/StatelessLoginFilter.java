package org.bojarski.sozz.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bojarski.sozz.dto.LoginForm;
import org.bojarski.sozz.model.Account;
import org.bojarski.sozz.security.AccountAuthentication;
import org.bojarski.sozz.security.AuthenticationToken;
import org.bojarski.sozz.security.CurrentUser;
import org.bojarski.sozz.service.TokenAuthorizationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Klasa bezstanowego filtru logowania.
 * @author Arkadiusz Bojarski
 *
 */
public class StatelessLoginFilter extends AbstractAuthenticationProcessingFilter {

    private final TokenAuthorizationService tokenAuthorizationService;
    private final UserDetailsService userDetailsService;
    
    /**
     * Konstruktor zapamiętujący podane parametry.
     * @param urlMapping napis stanowiący adres pod którym aplikacja będzie oczekiwać na zapytania zawierające dane uwierzytelniające.
     * @param tokenAuthorizationService {@link TokenAuthorizationService} obsługujący uwierzytelnianie za pomocą {@link AuthenticationToken}.
     * @param userDetailsService {@link UserDetailsService} obsługujący {@link CurrentUser}.
     * @param authenticationManager {@link AuthenticationManager} obsługujący właściwą walidację danych uwierzytelniających.
     */
    public StatelessLoginFilter(String urlMapping, TokenAuthorizationService tokenAuthorizationService,
            UserDetailsService userDetailsService, AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(urlMapping));
        this.userDetailsService = userDetailsService;
        this.tokenAuthorizationService = tokenAuthorizationService;
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException,
            IOException, ServletException {
        
        if(request.getMethod().equals("OPTIONS")) {
            return null;
        }
        
        final LoginForm form = new ObjectMapper().readValue(request.getInputStream(), LoginForm.class);
        final UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(
                form.getUsername(), form.getPassword());
        return getAuthenticationManager().authenticate(loginToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
            HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        final Account authenticatedAccount = ((CurrentUser) userDetailsService.loadUserByUsername(authResult.getName())).getAccount();
        final AccountAuthentication accountAuthentication = new AccountAuthentication(authenticatedAccount);
        
        tokenAuthorizationService.addAuthentication(response, accountAuthentication);
        
        SecurityContextHolder.getContext().setAuthentication(accountAuthentication);
    }
}
