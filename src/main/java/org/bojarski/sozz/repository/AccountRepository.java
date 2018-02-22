package org.bojarski.sozz.repository;

import org.bojarski.sozz.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * Interfejs repozytorium kont użytkownika.
 * @author Arkadiusz Bojarski
 *
 */
public interface AccountRepository extends JpaRepository<Account, Long>, QueryDslPredicateExecutor<Account> {
    
    /**
     * Metoda zwracającej konto użytkownika o podanej nazwie.
     * @param username napis będący nazwą użytkownika którego konto ma być zwrócone.
     * @return konto użytkownika o podanej nazwie.
     */
    public Account findOneByUsername(String username);
    
    /**
     * Metoda zwracająca konto użytkownika o podanym adresie email.
     * @param email napis będący adresem email użytkownika którego konto ma być zwrócone.
     * @return konto użytkownika o podanym adresie email.
     */
    public Account findOneByEmail(String email);
    
}
