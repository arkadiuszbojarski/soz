package org.bojarski.sozz.repository.account;

import org.bojarski.sozz.model.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface AccountRepository extends JpaRepository<Account, Long>, QueryDslPredicateExecutor<Account> {
    
    public Account findOneByUsername(String username);
    
    public Account findOneByEmail(String email);
    
    //public Page<Account> findByUsernameContaining(String username, Pageable pageable);

}
