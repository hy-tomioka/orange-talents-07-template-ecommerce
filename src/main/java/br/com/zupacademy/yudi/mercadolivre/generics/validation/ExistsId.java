package br.com.zupacademy.yudi.mercadolivre.generics.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Verifies if fieldName exists in domainClass.
 * Ignores null fields.
 */

@Constraint(validatedBy = {ExistsIdValidator.class})
@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
public @interface ExistsId {

    String message() default "Identifier does not exists.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    Class<?> domainClass();

    String fieldName();

}
