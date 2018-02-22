package org.bojarski.sozz.service;

import java.util.Optional;

import org.bojarski.sozz.dto.AccountCreateForm;
import org.bojarski.sozz.dto.AccountUpdateForm;
import org.bojarski.sozz.dto.ChangePasswordForm;
import org.bojarski.sozz.exception.AlreadyExistsException;
import org.bojarski.sozz.exception.NotFoundException;
import org.bojarski.sozz.exception.WrongPasswordException;
import org.bojarski.sozz.messages.Messages;
import org.bojarski.sozz.model.Account;
import org.bojarski.sozz.model.QAccount;
import org.bojarski.sozz.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;

/**
 * Implementacja interfejs konta użytkownika.
 * @author Arkadiusz Bojarski
 *
 */
@Service
@Transactional(readOnly = true)
public class DefaultAccountService implements AccountService {
    
    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    
    @Autowired
    public DefaultAccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Page<Account> search(String query, PageRequest pageRequest) {
        QAccount account = QAccount.account;
        BooleanBuilder where = new BooleanBuilder();
        
        if(query != null) {
            where.and(account.username.containsIgnoreCase(query))
            .or(account.email.containsIgnoreCase(query));
        }
        
        return accountRepository.findAll(where, pageRequest);
    }
    
    @Override
    public Account getByUsername(String username) {
        return accountRepository.findOneByUsername(username);
    }

    @Override
    @Transactional(readOnly = false)
    public Account create(AccountCreateForm accountCreateForm) {
        Account account = new Account();
        account.setUsername(accountCreateForm.getUsername());
        account.setEmail(accountCreateForm.getEmail());
        account.setPassword(encoder.encode(accountCreateForm.getPassword()));
        return accountRepository.save(account);
    }

    @Override
    public Account read(Long id) {
        return Optional.ofNullable(accountRepository.findOne(id))
                .orElseThrow(() -> new NotFoundException(Messages.ACCOUNT_NOT_FOUND, id.toString(), Messages.ACCOUNT_NOT_FOUND_DEFAULT));
    }
    
    @Override
    @Transactional(readOnly = false)
    public void changePassword(String name, ChangePasswordForm form) {
        Account edited = Optional.ofNullable(accountRepository.findOneByUsername(name))
                .orElseThrow(() -> new NotFoundException(Messages.ACCOUNT_NOT_FOUND, name, Messages.ACCOUNT_NOT_FOUND_DEFAULT));
        
        if(!new BCryptPasswordEncoder().matches(form.getPasswordOld(), edited.getPassword())) {
            throw new WrongPasswordException(Messages.WRONG_PASSWORD, Messages.WRONG_PASSWORD_DEFAULT);
        } else {
            edited.setPassword(new BCryptPasswordEncoder().encode(form.getPassword()));
            accountRepository.save(edited);
        }
        
    }

    @Override
    @Transactional(readOnly = false)
    public Account update(Long id, AccountUpdateForm form) {
        Account updated = read(id);
        Account existingByName = accountRepository.findOneByUsername(form.getUsername());
        Account existingByEmail = accountRepository.findOneByEmail(form.getEmail());
        
        if(existingByName != null && updated != existingByName) {
            throw new AlreadyExistsException(Messages.ACCOUNT_USERNAME_EXISTS, Messages.USERNAME, Messages.ACCOUNT_USERNAME_EXISTS_DEFAULT);
        }
        
        if(existingByEmail != null && updated != existingByEmail) {
            throw new AlreadyExistsException(Messages.ACCOUNT_EMAIL_EXISTS, Messages.EMAIL, Messages.ACCOUNT_EMAIL_EXISTS_DEFAULT);
        }
        
        updated.setUsername(form.getUsername());
        updated.setEmail(form.getEmail());
        updated.setEnabled(form.getEnabled());
        updated.setLocked(form.getLocked());
        updated.setRole(form.getRole());
        
        return accountRepository.save(updated);
    }

}
