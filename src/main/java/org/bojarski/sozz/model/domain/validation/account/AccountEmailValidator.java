package org.bojarski.sozz.model.domain.validation.account;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.bojarski.sozz.messages.Messages;
import org.bojarski.sozz.model.domain.account.AccountIdentityForm;
import org.bojarski.sozz.repository.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Klasa walidatora sprawdzającego spełnienie kryterium unikalności adresu email konta użytkownika.
 * @author Arkadiusz Bojarski
 *
 */
public class AccountEmailValidator implements ConstraintValidator<UniqueAccountEmail, AccountIdentityForm> {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountEmailValidator(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    
    @Override
    public void initialize(UniqueAccountEmail constraintAnnotation) {
        
    }

    @Override
    public boolean isValid(AccountIdentityForm value, ConstraintValidatorContext context) {
        boolean isValid = value == null || !Optional.ofNullable(accountRepository.findOneByEmail(value.getEmail())).isPresent();
        
        if(!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(Messages.ACCOUNT_EMAIL_EXISTS_DEFAULT)
            .addPropertyNode(Messages.EMAIL)
            .addConstraintViolation();
        }
        
        return isValid;
    }
    
}
