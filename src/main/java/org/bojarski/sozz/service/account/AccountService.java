package org.bojarski.sozz.service.account;

import org.bojarski.sozz.model.domain.account.Account;
import org.bojarski.sozz.model.domain.account.AccountCreateForm;
import org.bojarski.sozz.model.domain.account.AccountUpdateForm;
import org.bojarski.sozz.model.domain.account.ChangePasswordForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface AccountService {

    Page<Account> search(String username, PageRequest pageRequest);
    
    Account create(AccountCreateForm accountCreateForm);
    
    Account read(Long id);

    void changePassword(String name, ChangePasswordForm form);
    
    Account update(Long id, AccountUpdateForm form);
    
    Account getByUsername(String username);
    
}
