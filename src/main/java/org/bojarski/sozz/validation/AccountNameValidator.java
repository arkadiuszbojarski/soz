package org.bojarski.sozz.validation;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.bojarski.sozz.dto.AccountIdentityForm;
import org.bojarski.sozz.messages.Messages;
import org.bojarski.sozz.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Klasa walidatora sprawdzającego spełnienie kryterium unikalności nazwy konta użytkownika.
 * @author Arkadiusz Bojarski
 *
 */
public class AccountNameValidator implements ConstraintValidator<UniqueAccountName, AccountIdentityForm> {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountNameValidator(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    
    @Override
    public void initialize(UniqueAccountName constraintAnnotation) {
        
    }

    @Override
    public boolean isValid(AccountIdentityForm value, ConstraintValidatorContext context) {
        boolean isValid = value == null || !Optional.ofNullable(accountRepository.findOneByUsername(value.getUsername())).isPresent();
        
        if(!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(Messages.ACCOUNT_USERNAME_EXISTS_DEFAULT)
            .addPropertyNode(Messages.USERNAME)
            .addConstraintViolation();
        }
        
        return isValid;
    }
    
}