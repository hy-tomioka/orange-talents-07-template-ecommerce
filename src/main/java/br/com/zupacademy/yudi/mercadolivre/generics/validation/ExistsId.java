package br.com.zupacademy.yudi.mercadolivre.generics.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = {ExistsIdValidator.class})
@Target({FIELD})
@Retention(RUNTIME)
public @interface ExistsId {

    String message() default "Identifier does not exists.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    Class<?> domainClass();

}
