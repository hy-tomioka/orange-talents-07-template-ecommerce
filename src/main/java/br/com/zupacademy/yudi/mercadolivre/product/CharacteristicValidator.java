package br.com.zupacademy.yudi.mercadolivre.product;

import br.com.zupacademy.yudi.mercadolivre.product.dto.NewProductRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * This Validator validates *just* identical characteristics names on a single NewProductRequest instance.
 */
@Component
public class CharacteristicValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return NewProductRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) return;
        NewProductRequest request = (NewProductRequest) target;
        if (request.hasIdenticalCharacteristicsNames()) {
            errors.rejectValue("characteristics", null,
                    "Identical characteristics names are not permitted");
        }
    }
}
