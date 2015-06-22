package org.bojarski.sozz;


import org.bojarski.sozz.model.domain.account.Account;
import org.bojarski.sozz.model.domain.account.Role;
import org.bojarski.sozz.repository.account.AccountRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Klasa główna aplikacji.
 * 
 * @author Arkadiusz Bojarski
 *
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    
    @Autowired
    private AccountRepository accountRepository;
    
    public static void main( String[] args ) throws Exception {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    public InitializingBean insertDefaultUsers() {
        return new InitializingBean() {
            
            @Override
            public void afterPropertiesSet() throws Exception {
                
                if(accountRepository.count() == 0) {
                    Account testAdmin = new Account();
                    testAdmin.setUsername("admin");
                    testAdmin.setEmail("admin@domain.com");
                    testAdmin.setPassword(new BCryptPasswordEncoder().encode("password"));
                    testAdmin.setRole(Role.ADMIN);
                    testAdmin.setEnabled(true);
                    testAdmin.setLocked(false);
                    accountRepository.save(testAdmin);
                }
            }
        };
    }
}
