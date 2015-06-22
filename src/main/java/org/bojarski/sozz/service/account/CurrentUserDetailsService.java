package org.bojarski.sozz.service.account;

import java.util.Optional;

import org.bojarski.sozz.messages.Messages;
import org.bojarski.sozz.model.domain.account.Account;
import org.bojarski.sozz.model.domain.account.CurrentUser;
import org.bojarski.sozz.model.exception.NotFoundException;
import org.bojarski.sozz.repository.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;
    
    @Autowired
    public CurrentUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) {
        Account account = Optional
                .ofNullable(accountRepository.findOneByUsername(username))
                .orElseThrow(() -> new NotFoundException(Messages.ACCOUNT_NOT_FOUND, Messages.EMAIL, Messages.ACCOUNT_NOT_FOUND_DEFAULT));
        return new CurrentUser(account);
    }

}
