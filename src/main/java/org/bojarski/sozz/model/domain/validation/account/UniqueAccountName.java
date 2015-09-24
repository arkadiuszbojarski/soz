package org.bojarski.sozz.model.domain.validation.account;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.bojarski.sozz.messages.Messages;

/**
 * Adnotacja kryterium unikalności nazwy konta użytkownika.
 * @author Arkadiusz Bojarski
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AccountNameValidator.class)
public @interface UniqueAccountName
{
    
    String message() default Messages.ACCOUNT_USERNAME_EXISTS;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
