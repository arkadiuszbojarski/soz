package org.bojarski.sozz.service.authorization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bojarski.sozz.model.authentication.AccountAuthentication;
import org.springframework.security.core.Authentication;

public interface TokenAuthorizationService {
    
    void addAuthentication(HttpServletResponse response, AccountAuthentication authentication);
    
    Authentication getAuthentication(HttpServletRequest request);
    
}
