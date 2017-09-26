package org.bojarski.sozz.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Klasa prostego filtru CORS. 
 * @author Arkadiusz Bojarski
 *
 */
public class CORSFilter implements Filter {
    
    private final static String HOUR = Integer.toString(1000 * 60 * 60);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.addHeader("Access-Control-Max-Age", HOUR);
        response.addHeader("Access-Control-Allow-Headers", "Content-Type, X-Requested-With, X-Auth-Token");
        response.addHeader("Access-Control-Expose-Headers", "X-Auth-Token");
        
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {}

}
