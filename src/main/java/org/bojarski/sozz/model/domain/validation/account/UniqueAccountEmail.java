package org.bojarski.sozz.model.domain.validation.account;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.bojarski.sozz.messages.Messages;

/**
 * Adnotacja kryterium unikalności adresu email konta.
 * @author Arkadiusz Bojarski
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AccountEmailValidator.class)
public @interface UniqueAccountEmail
{
    
    String message() default Messages.ACCOUNT_EMAIL_EXISTS;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}