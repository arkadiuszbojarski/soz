package org.bojarski.sozz.config;

import org.bojarski.sozz.filter.StatelessAuthenticationFilter;
import org.bojarski.sozz.filter.StatelessLoginFilter;
import org.bojarski.sozz.service.EntryPoint;
import org.bojarski.sozz.service.TokenAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Klasa zawierająca konfigurację aplikacji.
 * 
 * @author Arkadiusz Bojarski
 *
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private TokenAuthorizationService tokenAuthenticationService;
    
    @Autowired
    private EntryPoint entryPoint;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
        .userDetailsService(userDetailsService)
        .passwordEncoder(new BCryptPasswordEncoder());
    }
    
    /* (non-Javadoc)
     * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.csrf().disable()
    	.anonymous().and()
    	.servletApi().and()
    	.headers().cacheControl().and().and()
    	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
    	.exceptionHandling().authenticationEntryPoint(entryPoint).and()
    	.authorizeRequests()
    	.antMatchers("/").permitAll()
    	.antMatchers(HttpMethod.OPTIONS, "/api/**").permitAll()
    	.antMatchers(HttpMethod.POST, "/api/login").permitAll()
    	.antMatchers("/api/**").authenticated().and()
    	.addFilterBefore(new StatelessAuthenticationFilter(tokenAuthenticationService), UsernamePasswordAuthenticationFilter.class)
    	.addFilterBefore(new StatelessLoginFilter("/api/login", tokenAuthenticationService, userDetailsService, authenticationManager()), StatelessAuthenticationFilter.class);
    }
    
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//        .anonymous().and()
//        .servletApi().and()
//        .headers().cacheControl().and()
//        //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//        //.exceptionHandling().authenticationEntryPoint(entryPoint).and()
//        .authorizeRequests()
//        .antMatchers("/").permitAll()
//        .antMatchers(HttpMethod.OPTIONS, "/api/**").permitAll()
//        .antMatchers(HttpMethod.POST, "/api/login").permitAll()
//        .antMatchers("/api/**").authenticated().and()
//        .addFilterBefore(new StatelessAuthenticationFilter(tokenAuthenticationService), UsernamePasswordAuthenticationFilter.class)
//        .addFilterBefore(new StatelessLoginFilter("/api/login", tokenAuthenticationService, userDetailsService, authenticationManager()), StatelessAuthenticationFilter.class);
//    }
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Override
    protected UserDetailsService userDetailsService() {
        return this.userDetailsService;
    }

}
