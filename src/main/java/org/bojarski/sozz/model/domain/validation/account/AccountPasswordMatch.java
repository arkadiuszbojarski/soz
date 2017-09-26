package org.bojarski.sozz.model.domain.validation.account;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.bojarski.sozz.messages.Messages;

/**
 * Kryterium zgodności hasła i powtórzonego hasła.
 * @author Arkadiusz Bojarski
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AccountPasswordMatchValidator.class)
public @interface AccountPasswordMatch
{
    
    String message() default Messages.PASSWORDS_DO_NOT_MATCH;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
