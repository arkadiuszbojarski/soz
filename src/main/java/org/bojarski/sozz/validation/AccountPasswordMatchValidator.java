package org.bojarski.sozz.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.bojarski.sozz.dto.AccountPasswordForm;
import org.bojarski.sozz.messages.Messages;

/**
 * Klasa walidatora sprawdzającego spełnienie kryteirum zgodności hasła i hasła powtórzonego konta użytkownika.
 * @author Arkadiusz Bojarski
 *
 */
public class AccountPasswordMatchValidator implements ConstraintValidator<AccountPasswordMatch, AccountPasswordForm> {
    
    @Override
    public void initialize(AccountPasswordMatch constraintAnnotation) {

    }

    @Override
    public boolean isValid(AccountPasswordForm value, ConstraintValidatorContext context) {
        
        boolean isValid = false;
        if(value == null) {
            isValid = true;
        } else if(value.getPassword() != null) {
            isValid = value.getPassword().equals(value.getPasswordRepeated());
        } else if(value.getPassword() == null && value.getPasswordRepeated() == null) {
            isValid = true;
        }
        
        if(!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(Messages.PASSWORDS_DO_NOT_MATCH_DEFAULT)
            .addPropertyNode(Messages.PASSWORD_REPEATED)
            .addConstraintViolation();
        }
        
        return isValid;
    }

}
